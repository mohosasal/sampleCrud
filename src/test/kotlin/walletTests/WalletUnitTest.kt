package walletTests

import com.example.tryone.TryOneApplication
import com.example.tryone.model.wallet.Wallet
import com.example.tryone.repository.WalletRepository
import com.example.tryone.service.WalletService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.*


@SpringBootTest(classes = [TryOneApplication::class])
@ExtendWith(MockitoExtension::class)
class WalletUnitTest {
    @MockBean
    private lateinit var walletRepository: WalletRepository

    @Autowired
    private lateinit var walletService: WalletService

    @Test
    fun createWalletTest() {
        //arrange
        var walletTemp1: Wallet = Wallet().also { it.name = "rez" }

        Mockito.`when`(walletRepository.save(walletTemp1)).thenAnswer {
            walletTemp1.id = 1
            return@thenAnswer walletTemp1
        }
        //act
        val wallet = walletService.create(walletTemp1)
        //assert
        assertThat(walletTemp1).isEqualTo(wallet);
    }

    @Test
    fun findWalletTest() {
        //arrange
        var walletTemp1: Wallet = Wallet().also {
            it.name = "rez";
            it.id=1
        }
        Mockito.`when`(walletRepository.findById(walletTemp1.id!!)).thenReturn(Optional.of(walletTemp1))
        // act
        val wallet = walletService.find(walletTemp1.id!!)
        //assert
        assertThat(walletTemp1).isEqualTo(wallet);
    }
}
