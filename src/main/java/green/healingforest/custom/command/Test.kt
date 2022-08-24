package green.healingforest.custom.command

import green.healingforest.command.Command
import green.healingforest.custom.command.sub.test.TestGUI
import green.healingforest.custom.command.sub.test.TestNPC

class Test: Command(
    arrayOf(
        TestGUI(),
        TestNPC()
    ),
    true
)