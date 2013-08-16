package dumb.router

import scala.xml.NodeSeq

class NotFoundHandler extends Handler {
  override def handle(request: Request, response: Response) = {
    response setStatus 404

    val responseBody: NodeSeq = <html>
      <body>
        <h1>404</h1>
      </body>
    </html>
    responseBody toString
  }
}
