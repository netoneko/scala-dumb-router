package com.earldouglas.xwptemplate

import scala.xml.NodeSeq
import dumb.router._
import dumb.router.dsl._

class XwpTemplateServlet extends Servlet {
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
}
