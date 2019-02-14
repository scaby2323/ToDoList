class Task(details: String, status: Int = 1) {
    
    val taskDetails = details
    var taskStatus = status

    def getStatus() : String = {
        this.taskStatus match {
            case 1 => "To Do"
            case 2 => "In Progress"
            case 3 => "Completed"
        }
    }

    def setStatus(status: Int): Unit = {
        taskStatus = status
    }
    
    def updateStatus() : Task = {
        this.taskStatus match {
            case 1 => new Task(taskDetails, 2)
            case 2 => new Task(taskDetails, 3)
            case 3 => throw new IllegalArgumentException("Task is already completed")
        }
    }
    
    def prettyPrint(index : Int) : Unit = {
        println("Task " + index + ": " + taskDetails + " Status: " + getStatus())
    }
}