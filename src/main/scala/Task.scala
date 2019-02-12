class Task(deets: String, stat: Int) {
    
    val details = deets
    val status = stat
    
    def getStatus() : String = {
        this.status match {
            case 1 => "To Do"
            case 2 => "In Progress"
            case 3 => "Completed"
        }
    }
    
    def updateStatus() : Task = {
        this.status match {
            case 1 => new Task(details, 2)
            case 2 => new Task(details, 3)
            case 3 => throw new IllegalArgumentException("Task is already completed")
        }
    }
    
    def prettyPrint() : Unit = {
        println("Task: " + details + " Status: " + getStatus())
    }
}