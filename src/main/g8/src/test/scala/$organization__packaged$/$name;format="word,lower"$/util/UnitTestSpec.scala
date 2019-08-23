package $organization$.$name;format="word,lower"$.util

import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.SpanSugar
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, Matchers, WordSpec}
import org.scalatestplus.mockito.MockitoSugar

trait UnitTestSpec
  extends WordSpec
    with Matchers
    with ScalaFutures
    with MockitoSugar
    with SpanSugar
    with BeforeAndAfterEach
    with BeforeAndAfterAll
