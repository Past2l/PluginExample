package green.example.custom.command

import green.example.command.Command
import green.example.custom.command.sub.test.TestGUI
import green.example.custom.command.sub.test.TestNPC

class Test: Command(
    arrayOf(
        TestGUI(),
        TestNPC()
    ),
    true
)