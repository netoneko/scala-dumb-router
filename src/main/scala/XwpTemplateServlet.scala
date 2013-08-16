package com.earldouglas.xwptemplate

import scala.xml.NodeSeq
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import dumb.router._
import dumb.router.dsl._

class XwpTemplateServlet extends Servlet {
  get("/", new Handler {
    def handle(request: HttpServletRequest, response: HttpServletResponse) {
      val responseBody: NodeSeq = <html><body><h1>Hello, world!</h1></body></html>
      response.getWriter.write(responseBody.toString)
    }
  })

  get("/hello", (request: HttpServletRequest, response: HttpServletResponse) => {
      val responseBody: NodeSeq = <html><body><h1>Hello!</h1></body></html>
      response.getWriter.write(responseBody.toString)
    }
  )
}
