package ru.sample.data.repository.datasource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RuntimeEnvironment;

import ru.sample.data.net.SoftPosApi;
import ru.sample.data.repository.datasource.cache.IBanksCache;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class BankDataStoreFactoryTest {
    private static final int FAKE_BANK_ID = 123;

    private BankDataStoreFactory bankDataStoreFactory;

    @Mock
    private IBanksCache mockBanksCache;

    @Mock
    private SoftPosApi softPosApi;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        bankDataStoreFactory = new BankDataStoreFactory(softPosApi, mockBanksCache);
    }

    @Test
    public void testCreateDiskDataStore() {
        given(mockBanksCache.isCached(FAKE_BANK_ID)).willReturn(true);
        given(mockBanksCache.isExpired()).willReturn(false);

        IBankDataStore bankDataStore = bankDataStoreFactory.createWithCacheDetailsBank(FAKE_BANK_ID);

        assertThat(bankDataStore, is(notNullValue()));
        assertThat(bankDataStore, is(instanceOf(DiskBankDataStore.class)));

        verify(mockBanksCache).isCached(FAKE_BANK_ID);
        verify(mockBanksCache).isExpired();
    }

    @Test
    public void testCreateCloudDataStore() {
        given(mockBanksCache.isExpired()).willReturn(true);
        given(mockBanksCache.isCached(FAKE_BANK_ID)).willReturn(false);

        IBankDataStore bankDataStore = bankDataStoreFactory.createWithCacheDetailsBank(FAKE_BANK_ID);

        assertThat(bankDataStore, is(notNullValue()));
        assertThat(bankDataStore, is(instanceOf(CloudBankDataStore.class)));

        verify(mockBanksCache).isExpired();
    }
}
