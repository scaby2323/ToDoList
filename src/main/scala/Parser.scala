class Parser {

	def parse(input: String) : (String, String) = {
		input match {
			case in if in.startsWith("add") => ("add", input.substring(3).trim())
            case in if in.startsWith("remove") => ("remove", input.substring(6).trim())
            case in if in.startsWith("update") => ("update", input.substring(6).trim())
            case in if in.startsWith("category") => ("category", input.substring(8).trim())
			case in if in.startsWith("discount") => ("discount", (input.substring(8).trim()))
			case in if in.startsWith("total") => ("total", "")
            case in if in.startsWith("show") => ("show", "")
            case in if in.startsWith("quit") => ("quit", "")
			case in if in.startsWith("test") => ("test", "")
            case _ => ("error", "")
		}
	}
}