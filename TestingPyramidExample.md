**Context:**
  - Cloud backup system. Save data from users computer to the cloud.
  - To improve perfomance backup happens only in "Backup windows"
  - configured by user(e.g. 00:00 - 06.00)

**Requirements:**
  - As a user
  - In order to backup storage
  - I want to schedule backup task during Backup window

**Tasks:**
  - Must be inside Backup windows
  - Must not overlap
  - Have some max length (e.g. 00.30 - 02.00)

**Method:**
```
public class Scheduler {
  public BackupTask schedule(DateTime start, DateTime end){
  //Implementation
  }
}
```

**Acceptance Tests(Component level) :**
```
Scenario: Create Backup task during Backup Window
Given user has a Backup window 00:00-06.00
When user creats a Backup task at 00.30-02.00
Then task is created successfully

Scenario: Backup task must be inside Backup window
Give user has a Backup windows 00:00-06.00
When user creates a Backup task at 23.30-00.30
Then Error "BackupTaskShouldBeInBackupWindows" is shown

Scenario: Backup tasks must not overlap
Given user has a Backup window 00:00-06:00
And user has a Backup task 00:30-02:30
When user creates a Backup task 01:00-02:00
Then Error "BackupTasksMustNotOverlap" is shown
```

**Contract Testing (Component, Provider):**
```
GET /task/Existing -> 200, JSON of task
GET /task/Nonexisting -> 404, JSON of error
POST /task -> 201(Created), JSON of task
POST /task -> 400(Bad Request), JSON of error
PUT /task/Existing -> 200, JSON of task
PUT /task/NonExisting -> 200, JSON of task
PUT /task/Existing (negative) -> 400(Bad Request), JSON of ERROR
DELETE /task/Existing -> 200
DELETE /task/NonExisting -> 202(Accepted)
```

**Unit Tests:**
1. if start == null -> throw exception
2. if end == null -> throw exception
3. if end == start -> throw exception
4. if end < start -> throw exception
5. if task.start == windows.start -> ok
6. if task.end == window.end -> ok
7. oldTask.start == newTask.start -> exception
8. oldTask.end == newTask. start -> ok

**Integration:**

CRUD tests

**E2E:**
- Login -> Create Backup Window -> Create New Backup Task
- Also one negative test
