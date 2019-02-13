class Parser {
	def parse(input: String) : (String, String) = {
		input match {
			case in if in.startsWith("add") => ("add", input.substring(3).trim())
            case in if in.startsWith("remove") => ("remove", input.substring(6).trim())
            case in if in.startsWith("update") => ("update", input.substring(6).trim())
            case in if in.startsWith("completed") => ("completed", "")
            case in if in.startsWith("show") => ("show", "")
            case in if in.startsWith("quit") => ("quit", "")
            case _ => ("error", "")
		}
	}
}