package $organization$.$name;format="word,lower"$

import org.mockito.Mockito._
import org.mockito.ArgumentMatchers.{eq => mockEq, _}
import $organization$.$name;format="word,lower"$.util.UnitTestSpec

class MainTest extends UnitTestSpec {
  
  "Main" should {
    "say hello" in {
      //Given
      val expected = "Hello world!"
      
      //When
      val actual = Main.sayHello()

      //Then
      actual shouldEqual expected
    }
  }
}
