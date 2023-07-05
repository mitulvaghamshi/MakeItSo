package me.mitul.todo.screens.edit_task

enum class EditFlagOption {
    On,
    Off;

    companion object {
        fun getByCheckedState(checkedState: Boolean?): EditFlagOption =
            if (checkedState == true) On else Off

        fun getBooleanValue(flagOption: String): Boolean =
            flagOption == On.name

        fun getOptions(): List<String> {
            val options = mutableListOf<String>()
            values().forEach { flagOption ->
                options.add(flagOption.name)
            }
            return options
        }
    }
}
