package ru.sample.data.repository.datasource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ru.sample.data.repository.datasource.cache.IBanksCache;

import static org.mockito.Mockito.verify;

public class DiskBankDataStoreTest {
    private static final int FAKE_BANK_ID = 123;

    private DiskBankDataStore diskBankDataStore;

    @Mock
    private IBanksCache mockBankCache;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        diskBankDataStore = new DiskBankDataStore(mockBankCache);
    }

    @Test
    public void testGetBankDetailesFromCache() {
        diskBankDataStore.bankDescription(FAKE_BANK_ID);
        verify(mockBankCache).getBank(FAKE_BANK_ID);
    }

    @Test
    public void testGetBankListFromCache() {
        diskBankDataStore.listBanks();
        verify(mockBankCache).getBankList();
    }
}
