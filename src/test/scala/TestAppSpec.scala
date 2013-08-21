package dumb.router.test

import org.specs2.mutable._

import dumb.router._
import dumb.router.dsl._

class TestAppSpec extends Specification {

  class TestServlet extends Servlet

  get("/", (_, _) => {
    "Hello!"
  })

  post("/post", (req: Request, _: Response) => {
    s"Hello, ${req getParameter "name"}!"
  })

  get("/hello/:name/number/:num", (req: Request, _: Response) => {
    s"Hello, ${req getParameter "name"} #${req getParameter "num"}!"
  })

  post("/post/:num", (req: Request, _: Response) => {
    s"Hello, ${req getParameter "name"} #${req getParameter "num"}!"
  })


  val testServlet = new TestServlet()

  "TestServlet.get".should {
    val req = new FakeRequest("/")
    val res = new FakeResponse

    testServlet.getRequest(req, res)

    "respond with code 200" in {
      res.code mustEqual (200)
    }

    "return expected message" in {
      res.body mustEqual ("Hello!")
    }
  }

  "TestServlet.post".should {
    val req = new FakeRequest("/post", scala.collection.mutable.Map("name" -> Array("Test")))
    val res = new FakeResponse

    testServlet.postRequest(req, res)

    "respond with code 200" in {
      res.code mustEqual (200)
    }

    "return expected message" in {
      res.body mustEqual ("Hello, Test!")
    }
  }

  "TestServlet.get with url params".should {
    val req = new FakeRequest("/hello/servlet/number/1")
    val res = new FakeResponse

    testServlet.getRequest(req, res)

    "set params" in {
      res.code mustEqual (200)
    }

    "return expected message" in {
      res.body mustEqual ("Hello, servlet #1!")
    }
  }

  "TestServlet.post with url params".should {
    val req = new FakeRequest("/post/1", scala.collection.mutable.Map("name" -> Array("Test")))
    val res = new FakeResponse

    testServlet.postRequest(req, res)

    "set params" in {
      res.code mustEqual (200)
    }

    "return expected message" in {
      res.body mustEqual ("Hello, Test #1!")
    }
  }

}
