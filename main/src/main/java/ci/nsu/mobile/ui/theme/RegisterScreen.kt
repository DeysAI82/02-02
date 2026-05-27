package ci.nsu.mobile.main.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ci.nsu.mobile.main.model.*
import ci.nsu.mobile.main.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController) {

    val viewModel: AuthViewModel = viewModel()

    val groupsState = viewModel.groups.observeAsState()
    val groups = groupsState.value ?: emptyList()

    val error by viewModel.error.observeAsState()

    var expanded by remember { mutableStateOf(false) }
    var selectedGroup by remember { mutableStateOf<GroupDto?>(null) }

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var middleName by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }

    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.loadGroups()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        Text(
            "Регистрация",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(firstName, { firstName = it }, label = { Text("Имя") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(lastName, { lastName = it }, label = { Text("Фамилия") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(middleName, { middleName = it }, label = { Text("Отчество") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(birthDate, { birthDate = it }, label = { Text("Дата рождения") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(gender, { gender = it }, label = { Text("Пол") }, modifier = Modifier.fillMaxWidth())

        Spacer(Modifier.height(12.dp))

        // ===== GROUP DROPDOWN (СТАБИЛЬНЫЙ) =====
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {

            OutlinedTextField(
                readOnly = true,
                value = selectedGroup?.name ?: "Выберите группу",
                onValueChange = {},
                label = { Text("Группа") },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {

                if (groups.isEmpty()) {
                    DropdownMenuItem(
                        text = { Text("Загрузка...") },
                        onClick = {}
                    )
                } else {
                    groups.forEach { group ->
                        DropdownMenuItem(
                            text = { Text(group.name) },
                            onClick = {
                                selectedGroup = group
                                expanded = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(login, { login = it }, label = { Text("Логин") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(password, { password = it }, label = { Text("Пароль") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(email, { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(phone, { phone = it }, label = { Text("Телефон") }, modifier = Modifier.fillMaxWidth())

        Spacer(Modifier.height(20.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {

                val person = PersonDto(
                    firstName = firstName,
                    lastName = lastName,
                    middleName = middleName,
                    birthDate = birthDate,
                    gender = gender,
                    groupId = selectedGroup?.id ?: 1
                )

                val request = RegisterRequest(
                    login = login,
                    password = password,
                    email = email,
                    phoneNumber = phone,
                    roleId = 1,
                    authAllowed = true,
                    person = person
                )

                viewModel.register(request) {
                    navController.popBackStack()
                }
            }
        ) {
            Text("Зарегистрироваться")
        }

        error?.let {
            Spacer(Modifier.height(10.dp))
            Text(it, color = MaterialTheme.colorScheme.error)
        }
    }
}