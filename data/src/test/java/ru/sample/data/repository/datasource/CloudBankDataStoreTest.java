package ru.sample.data.repository.datasource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import ru.sample.data.net.SoftPosApi;
import ru.sample.data.repository.datasource.cache.IBanksCache;
import ru.sample.domain.entity.FullBankEntity;
import ru.sample.domain.entity.ShortBankEntity;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CloudBankDataStoreTest {
    private static final int FAKE_BANK_ID = 421;

    private CloudBankDataStore cloudBankDataStore;

    @Mock
    private SoftPosApi softPosApi;

    @Mock
    private IBanksCache mockBankCache;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        cloudBankDataStore = new CloudBankDataStore(softPosApi, mockBankCache);
    }

    @Test
    public void testGetBankListFromApi() {
        List<ShortBankEntity> shortBankEntities = new ArrayList<>();
        given(softPosApi.listBanks()).willReturn(Observable.just(shortBankEntities));
        cloudBankDataStore.listBanks().subscribe();
        verify(softPosApi).listBanks();
        verify(mockBankCache).putBankList(shortBankEntities);
    }

    @Test
    public void testGetBankDetailsFromApi() {
        FullBankEntity fullBankEntity = mock(FullBankEntity.class);
        Observable<FullBankEntity> fakeObservable = Observable.just(fullBankEntity);
        given(softPosApi.bankDescription(FAKE_BANK_ID)).willReturn(fakeObservable);
        cloudBankDataStore.bankDescription(FAKE_BANK_ID).subscribe();
        verify(softPosApi).bankDescription(FAKE_BANK_ID);
        verify(mockBankCache).putBank(fullBankEntity);
    }
}
