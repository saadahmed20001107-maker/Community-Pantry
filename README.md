# Community Pantry Management System - Version 1

BIT1244 Software Fundamentals, Individual Assignment (Sep-Dec 2026)
Student: Saad Ahmed (SCSJ2600401)

## Requirements
- JDK 17 or newer (run and checked in Eclipse with JDK 25)
- Eclipse IDE for Java Developers (console interface only in this part)

## Main class
`Main` (default package; keep all the `.java` files together in the project's `src` folder)

## How to run in Eclipse
1. In Eclipse, choose File > New > Java Project, give it a name (for example `Community_pantry`) and untick "Create module-info.java".
2. Copy all the `.java` files from this repository into the new project's `src` folder (default package).
3. Right-click `Main.java` > Run As > Java Application.
4. Type the menu number in the Console tab and press Enter.
5. Quick demo: choose 8 (load sample data), 4 (process), 4 again (no change), 5 (history), 6 with threshold 2.

## Files
| File | Role (from Part B) |
|---|---|
| Main.java | Menu / user interface |
| Validator.java | Input / validation |
| ItemManager.java | Item management |
| RequestManager.java | Request management |
| Storage.java | Storage (HashMap, Queue/ArrayDeque, ArrayList) |
| Processor.java | Processing (FIFO) |
| Reporter.java | Reporting |
| Item.java, Request.java, ProcessResult.java | Data classes |
| TestData.md | Assignment dataset and the 14 test inputs with expected results |

## Notes
- All data is invented pantry data and stays in memory (no save/load in Version 1).
- AI assistance: the first draft of the code was produced with Claude from my Part A and Part B design. I read it, traced the dataset by hand and ran it myself to check it matches my Part B trace.
