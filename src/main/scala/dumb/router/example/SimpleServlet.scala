package dumb.router.example

import scala.xml.NodeSeq
import dumb.router._

class SimpleServlet extends Servlet {
  get("/", new Handler {
    def handle(request: Request, response: Response) = {
      val responseBody: NodeSeq = <html><body><h1>Hello, world!</h1></body></html>
      responseBody
    }
  })

  get("/hello", (request: Request, response: Response) => {
      val responseBody: NodeSeq = <html><body><h1>Hello!</h1></body></html>
      responseBody
    }
  )

  get("/hello/:name", (req: Request, _: Response) => {
    s"Hello, ${req getParameter "name"}!"
  })

}
