package ru.sample.data.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import ru.sample.data.repository.datasource.BankDataStoreFactory;
import ru.sample.data.repository.datasource.IBankDataStore;
import ru.sample.domain.entity.FullBankEntity;
import ru.sample.domain.entity.ShortBankEntity;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BanksRepositoryTest {

    private static final int FAKE_BANK_ID = 123;

    private BanksRepository banksRepository;

    @Mock private BankDataStoreFactory mockBankDataStoreFactory;
    @Mock private IBankDataStore mockBankDataStore;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        banksRepository = new BanksRepository(mockBankDataStoreFactory);
        given(mockBankDataStoreFactory.createWithCacheListBanks()).willReturn(mockBankDataStore);
        given(mockBankDataStoreFactory.createWithCacheDetailsBank(anyInt())).willReturn(mockBankDataStore);
    }

    @Test
    public void testGetListBanks() {

        List<ShortBankEntity> bankEntityList = new ArrayList<>();
        bankEntityList.add(mock(ShortBankEntity.class));

        given(mockBankDataStore.listBanks()).willReturn(Observable.just(bankEntityList));

        banksRepository.listBanks();

        verify(mockBankDataStoreFactory).createWithCacheListBanks();
        verify(mockBankDataStore).listBanks();
    }

    @Test
    public void testGetBankDescription() {
        FullBankEntity fullBankEntity = mock(FullBankEntity.class);
        given(mockBankDataStore.bankDescription(FAKE_BANK_ID)).willReturn(Observable.just(fullBankEntity));
        banksRepository.bankDescription(FAKE_BANK_ID);
        verify(mockBankDataStoreFactory).createWithCacheDetailsBank(FAKE_BANK_ID);
        verify(mockBankDataStore).bankDescription(FAKE_BANK_ID);
    }
}
