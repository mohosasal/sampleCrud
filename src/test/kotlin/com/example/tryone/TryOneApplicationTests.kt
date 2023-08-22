package com.example.tryone

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension


@SpringBootTest
@ExtendWith(SpringExtension::class)
class TryOneApplicationTests {

//    @Autowired
//    private lateinit var mockMvc: MockMvc
//
//    @Autowired
//    private lateinit var transactionService: TransactionService
//
//    @SpyBean
//    private lateinit var transactionRepository: TransactionRepository

 //   @Test
//    fun contextLoads() {
//        Mockito.`when`(transactionRepository.save(Transaction())).thenReturn(Transaction())
//
//        transactionService.transactionCreate(Transaction())
//        Mockito.verify(transactionRepository.save(Transaction()))
//
//        val result = mockMvc.perform(
//            post("/api/wallets")
//                .accept(MediaType.APPLICATION_JSON)
//                .content("{\n\"name\": \"ali\"\n}")
//        ).andExpect(status().isOk)
//            .andReturn()
//        result.response.contentAsString
//        // assertNotNull


 //   }
}
