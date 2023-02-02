```mermaid
flowchart LR
 WBR[Web Browser]
 CLN[WildFly\nApplication Client]
 APP[WildFly\nJSF Application]:::orangeBox

 WBR & CLN <--> APP
 
 classDef orangeBox  fill:#ffa500,stroke:#000,stroke-width:3px
```