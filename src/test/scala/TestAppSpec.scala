package dumb.router.test

import org.specs2.mutable._

import dumb.router._

class TestAppSpec extends Specification {

  class TestServlet extends Servlet {
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

    put("/put", (req: Request, _: Response) => {
      s"Hello, ${req getParameter "name"}!"
    })

    put("/poot/:num", (req: Request, _: Response) => {
      s"Hello, ${req getParameter "name"} #${req getParameter "num"}!"
    })

    delete("/del", (req: Request, _: Response) => {
      s"Hello, ${req getParameter "name"}!"
    })

    delete("/del/:num", (req: Request, _: Response) => {
      s"Hello, ${req getParameter "name"} #${req getParameter "num"}!"
    })
  }

  val testServlet = new TestServlet()

  "TestServlet.get".should {
    "without url params".should {
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

    "with url params".should {
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
  }

  "TestServlet.post".should {
    "without url params".should {
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

    "with url params".should {
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

  "TestServlet.put".should {
    "without url params".should {
      val req = new FakeRequest("/put", scala.collection.mutable.Map("name" -> Array("Poot")))
      val res = new FakeResponse

      testServlet.putRequest(req, res)

      "respond with code 200" in {
        res.code mustEqual (200)
      }

      "return expected message" in {
        res.body mustEqual ("Hello, Poot!")
      }
    }

    "with url params".should {
      val req = new FakeRequest("/poot/1", scala.collection.mutable.Map("name" -> Array("Poot")))
      val res = new FakeResponse

      testServlet.putRequest(req, res)

      "set params" in {
        res.code mustEqual (200)
      }

      "return expected message" in {
        res.body mustEqual ("Hello, Poot #1!")
      }
    }
  }

  "TestServlet.delete".should {
    "without url params".should {
      val req = new FakeRequest("/del", scala.collection.mutable.Map("name" -> Array("Dl")))
      val res = new FakeResponse

      testServlet.deleteRequest(req, res)

      "respond with code 200" in {
        res.code mustEqual (200)
      }

      "return expected message" in {
        res.body mustEqual ("Hello, Dl!")
      }
    }

    "with url params".should {
      val req = new FakeRequest("/del/1", scala.collection.mutable.Map("name" -> Array("Dl")))
      val res = new FakeResponse

      testServlet.deleteRequest(req, res)

      "set params" in {
        res.code mustEqual (200)
      }

      "return expected message" in {
        res.body mustEqual ("Hello, Dl #1!")
      }
    }
  }

}
