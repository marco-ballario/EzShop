# Project Estimation  
Authors:
Date:
Version:
# Contents
- [Estimate by product decomposition]
- [Estimate by activity decomposition ]
# Estimation approach
<Consider the EZGas  project as described in YOUR requirement document, assume that you are going to develop the project INDEPENDENT of the deadlines of the course>
# Estimate by product decomposition
### 
|             | Estimate                        |             
| ----------- | ------------------------------- |  
| NC =  Estimated number of classes to be developed   |              20               |             
|  A = Estimated average size per class, in LOC       |               300             | 
| S = Estimated size of project, in LOC (= NC * A) | 6000 |
| E = Estimated effort, in person hours (here use productivity 10 LOC per person hour)  |              600                        |   
| C = Estimated cost, in euro (here use 1 person hour cost = 30 euro) | 18'000€ | 
| Estimated calendar time, in calendar weeks (Assume team of 4 people, 8 hours per day, 5 days per week ) | 10 calendar weeks |               
# Estimate by activity decomposition
### 
|         Activity name    | Estimated effort (person hours)   |             
|  ------------------------| :-------------------------------: | 
| <Strong>Requirements</Strong>| |
| Read customer requests | 2 |
| Identify user needs | 4 |
| Identify stakeholders, actors and stories | 2 |
| Define functional requirements | 5 |
| Define performance requirement| 6 |
| Define use cases and scenarios | 15 |
| System design decision  | 50 |
| HW support requirement | 30 |
| Customer checks | 20 |
| <Strong>Design</Strong> | |
| Architectural pattern decision | 10 |
| Package diagram design | 5 |
| Low level design | 80 |
| Design pattern implementation | 10 |
| Low level design control and review | 10 |
| GUI design and prototype| 35 |
| <Strong>Implementation </Strong>| |
| Class and package implementation| 4 |
| Attributes and methods implementation | 70 |
| GUI implementation | 40 |
| <Strong>V&V</Strong>  ||
| Check of requirements | 15 |
| Check of design | 15 |
| Check of code | 20 |
| Test definitions | 30 |
| Test execution and error reporting | 20 |
| Error fixing & debugging | 40 |
| <Strong>Deployment</Strong>||
| Deployment on HW | 6 |
| Database deployment | 10 |
| Check of main functions | 4 |

Total = 528 person hours

###
```plantuml
@startgantt
-- Phases --
[Requirements] lasts 14 days
[Requirements] is colored in crimson
[Design] lasts 11 days
[Design] starts at [Requirements]'s end
[Design] is colored in GoldenRod
[Implementation] lasts 10 days
[Implementation] starts at [Design]'s end
[Implementation] is colored in ForestGreen
[V&V] lasts 14 days
[V&V] starts at [Implementation]'s end
[V&V] is colored in aqua
[Deployment] lasts 3 days
[Deployment] starts at [V&V]'s end
[Deployment] is colored in hotpink
-- Activities --

[Requirements doc] lasts 4 days
[System design] starts at [Requirements doc]'s end
[System design] lasts 6 days
[HW selection] starts at [System design]'s end
[HW selection] lasts 4 days
[Architectural pattern] lasts 2 days
[Architectural pattern] starts at [HW selection]'s end
[Package diagram] lasts 1 days
[Package diagram] starts at [Architectural pattern]'s end
[Low level design] starts at [Package diagram]'s end
[Design pattern] starts at [Package diagram]'s end
[Low level design] lasts 8 days
[Design pattern] lasts 2 days
[GUI design] lasts 3 days
[GUI design] starts at [Package diagram]'s end
[Classes implementation] lasts 1 days
[Classes implementation] starts at [Low level design]'s end
[Attrib/methods implem] lasts 9 days
[Attrib/methods implem] starts at [Classes implementation]'s end
[GUI implem] lasts 5 days
[GUI implem] starts at [Classes implementation]'s end

[Test definitions] lasts 4 days
[Test definitions] starts at [Attrib/methods implem]'s end
[Check requirem] lasts 2 days
[Check requirem] starts at [Attrib/methods implem]'s end
[Check design] lasts 2 days
[Check design] starts at [Check requirem]'s end
[Check code] lasts 3 days
[Check code] starts at [Check design]'s end

[Tests & debug] lasts 7 days
[Tests & debug] starts at [Check code]'s end

[Deployment HW] lasts 1 days
[Deployment HW] starts at [Tests & debug]'s end
[DB deploy] lasts 2 days
[DB deploy] starts at [Tests & debug]'s end
[Check main functions] lasts 1 days
[Check main functions] starts at [DB deploy]'s end
@endgantt
```
