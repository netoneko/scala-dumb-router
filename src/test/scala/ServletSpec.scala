package test.scala

import org.specs2.mutable._
import com.earldouglas.xwptemplate.XwpTemplateServlet

class ServletSpec extends Specification {
  val servlet = new XwpTemplateServlet()

  "servlet" should {
    "be instance of it's own class" in {
      servlet.isInstanceOf[XwpTemplateServlet] must beEqualTo(true)
    }
  }

}
