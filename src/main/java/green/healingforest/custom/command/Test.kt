package green.healingforest.custom.command

import green.healingforest.command.Command
import green.healingforest.custom.command.sub.test.TestGUI

class Test: Command(
    arrayOf(TestGUI()),
    true
)