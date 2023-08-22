package TransactionTests

import com.example.tryone.TryOneApplication
import com.example.tryone.exceptions.NotEnoughWalletBalanceException
import com.example.tryone.model.transaction.Transaction
import com.example.tryone.model.transaction.TransactionType
import com.example.tryone.model.wallet.Wallet
import com.example.tryone.repository.WalletRepository
import com.example.tryone.service.TransactionService
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean


@SpringBootTest(classes = [TryOneApplication::class])
@ExtendWith(MockitoExtension::class)
class TransactionUnitTest {
    @MockBean
    private lateinit var walletRepository: WalletRepository
    @Autowired
    private lateinit var transactionService: TransactionService

    @Test
    fun createInvalidTransaction() {
        //arrange
        val wallet : Wallet = Wallet().also {
            it.balance=15
            it.name="atefe"
            it.id=1
        }
        val transaction : Transaction = Transaction().also {
            it.amount=20
            it.id=1
            it.walletId=1
            it.transactionType= TransactionType.DECREASE
        }

        Mockito.`when`(walletRepository.existsById(transaction.id!!)).thenReturn(true)
        Mockito.`when`(walletRepository.getReferenceById(transaction.walletId!!)).thenReturn(wallet)
        //act
        val result= assertThrows(NotEnoughWalletBalanceException::class.java) {
            transactionService.transactionCreateValidate(transaction)
        }
        //assert
        assertEquals("not enough balance in wallet with id 1",result.message)
    }
}