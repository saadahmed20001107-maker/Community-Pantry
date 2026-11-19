# Community Pantry Management System - Version 1

BIT1244 Software Fundamentals, Individual Assignment (Sep-Dec 2026)
Student: Saad Ahmed (SCSJ2600401)

## Requirements
- JDK 17 or newer (written and checked with JDK 21)
- Eclipse IDE for Java Developers (console interface only in this part)

## Main class
`Main` (default package, in the `src` folder)

## How to run in Eclipse
1. File > Import > General > Existing Projects into Workspace, pick this folder.
2. Right-click `Main.java` > Run As > Java Application.
3. Type the menu number in the Console tab and press Enter.
4. Quick demo: choose 8 (load sample data), 4 (process), 4 again (no change), 5 (history), 6 with threshold 2.

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

## Notes
- All data is invented pantry data and stays in memory (no save/load in Version 1).
- AI assistance: the first draft of the code was produced with Claude from my Part A and Part B design. I read it, traced the dataset by hand and ran it myself to check it matches my Part B trace.
