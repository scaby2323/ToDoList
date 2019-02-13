class TaskList(taskList: List[Task] = Nil) {
    
    var tasks: List[Task] = taskList
    
    def addTask(task: String): Unit = {
        val count = tasksLength()
        val newTask = new Task(task, 1, count)
        tasks = tasks :+ newTask
    }

    def tasksLength() : Integer = {
        tasks.foldLeft(0)((acc: Int, task: Task) => acc + 1)
    }
    
    def removeTask(task: Integer): Unit = {
        tasks = removeFromList(0, tasks, task)
    }
    
    def updateTask(task: Integer) : Unit = {
        tasks = updateInList(0, tasks, task)
    } 
    
    def getCompleted() : TaskList = {
        return new TaskList(tasks.filter(t => t.taskStatus == 3))
    }

    def prettyPrint() : Unit = {
        // tasks.foldLeft("", 0)((acc: (String, Integer), task: Task) => (acc._1 + task.prettyPrint() + "\n", acc._2 + 1))

        tasks.foldLeft("")((acc: String, task: Task) => acc + task.prettyPrint() + "\n")
    }

    def removeFromList(currIdx: Integer, lst: List[Task], idxToRemove: Integer): List[Task] = {
        lst match {
            case (hd :: tl) => if (currIdx == idxToRemove) tl else (hd :: removeFromList(currIdx+1, tl, idxToRemove))
            case Nil => Nil
        }
    }
    
    def updateInList(currIdx: Integer, lst: List[Task], idxToUpdate: Integer): List[Task] = {
        lst match {
            case (hd :: tl) => if (currIdx == idxToUpdate) hd.updateStatus() :: tl else (hd :: updateInList(currIdx + 1, tl, idxToUpdate))
            case Nil => Nil 
            
        }
    }
}