package walletTests


import com.example.tryone.TryOneApplication
import com.example.tryone.model.wallet.Wallet
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.util.Assert

@SpringBootTest(classes = [TryOneApplication::class])
@AutoConfigureMockMvc
class WalletIntegrationTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun validWalletCreate() {

        //arrange
        val tempWallet: Wallet = Wallet().also { it.name = "reza" }

        //act
        val result = mockMvc.perform(
            post("/api/wallet").content(objectMapper.writeValueAsString(tempWallet))
                .contentType("application/json")
        )
            .andExpect(status().isOk()).andReturn()

        //assert
        Assert.notNull(result, "wallet create successfully")

    }

    @Test
    fun invalidWalletCreate() {

        //arrange
        var tempWallet: Wallet? = Wallet().also {
            it.name = "reza"
            it.balance = -2
        }

        //act
        var result = mockMvc.perform(
            post("/api/wallet").content(objectMapper.writeValueAsString(tempWallet))
                .contentType("application/json")
        )
            .andExpect(status().isBadRequest).andReturn()

        //assert
        Assert.notNull(result, "wallet not create successfully")
    }

}