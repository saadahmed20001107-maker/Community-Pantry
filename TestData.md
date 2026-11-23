# Test data - Community Pantry Management System, Version 1

Invented pantry data. Each test is typed at the menu starting from a fresh run.
"8" is the menu option that loads the assignment dataset (2 items, 3 pending requests).
Regression set (keep for the project): T3, T4, T5, T6 and the threshold-2 case of T12.

## Assignment dataset
Items:    R001 Rice packs 10 | O001 Oil bottles 4
Requests: Q001 R001 3 | Q002 O001 5 | Q003 R001 7
Low-stock threshold: 2
Expected after processing: Q001 FULFILLED (R001 7), Q002 REJECTED (O001 4), Q003 FULFILLED (R001 0)
Final stock: O001 = 4, R001 = 0. Low-stock report at 2: R001 only. Processing again: no stock change.

## Tests (menu inputs, one per line, in order)
T1  1 R001 "Rice packs" 10 | 1 O001 "Oil bottles" 4 | 2 R001 | 2 O001
T2  3 Q001 R001 3 | 3 Q002 O001 5 | 3 Q003 R001 7
T3  8, then 4
T4  (from T3) Q003 asks 7 of R001 with exactly 7 left -> FULFILLED, stock 0
T5  (from T3) Q002 asks 5 of O001 with 4 in stock -> REJECTED, stock 4
T6  8, 4, 4, 5
T7  8, 1 R001 "Rice packs" 99, 2 R001
T8  8, 4, 3 Q001 O001 1, 5
T9  1 R002 Test abc | 1 R002 Test 99999999999 | 8, 3 Q009 R001 abc | 6 abc
T10 stock 0, 1000 accepted; -1, 1001 refused | quantity 1, 1000 accepted; 0, 1001 refused
T11 8, 3 Q009 Z999 1 | 1 RR01 x 5 | 3 1001 R001 1 | 2 " r001 "
T12 8, 4, then option 6 with 2, 0, 4, -1
T13 name of 1 and 40 characters accepted; empty, spaces only and 41 characters refused
T14 1 O001 "Oil bottles" 4, then 8, then 2 R001, then 5
