package dumb.router.test

import org.specs2.mutable._

import dumb.router._
import dumb.router.dsl._

class TestAppSpec extends Specification {
  class TestServlet extends Servlet

  get("/", (Request, Response) => {
    "Hello!"
  })

  val testServlet = new TestServlet()

  "TestServlet".should {
    val req = new FakeRequest("/")
    val res = new FakeResponse

    testServlet.getRequest(req, res)

    "respond with code 200" in {
      res.code mustEqual(200)
    }

    "return expected message" in {
      res.body mustEqual("Hello!")
    }
  }
}
