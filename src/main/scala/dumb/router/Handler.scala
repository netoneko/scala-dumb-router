package dumb.router

trait Handler {
  def handle(request: Request, response: Response): Any
}
