package com.lifeos.messenger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Blue = Color(0xFF155EEF)
private val Ink = Color(0xFF182230)
private val Muted = Color(0xFF667085)
private val Pale = Color(0xFFF3F6FC)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { LifeOSApp() }
    }
}

@Composable
fun LifeOSApp() {
    var selected by remember { mutableStateOf("Home") }
    MaterialTheme(colorScheme = lightColorScheme(primary = Blue, background = Pale)) {
        Scaffold(
            containerColor = Pale,
            bottomBar = {
                NavigationBar(containerColor = Color.White) {
                    val tabs = listOf(
                        "Home" to Icons.Default.Home,
                        "Jobs" to Icons.Default.Work,
                        "Messages" to Icons.Default.Chat,
                        "Training" to Icons.Default.School,
                        "Profile" to Icons.Default.Person
                    )
                    tabs.forEach { (name, icon) ->
                        NavigationBarItem(
                            selected = selected == name,
                            onClick = { selected = name },
                            icon = { Icon(icon, contentDescription = name) },
                            label = { Text(name, fontSize = 10.sp) }
                        )
                    }
                }
            }
        ) { padding ->
            Column(Modifier.fillMaxSize().padding(padding)) {
                Header()
                when (selected) {
                    "Home" -> HomeScreen(
                        onJobs = { selected = "Jobs" },
                        onMessages = { selected = "Messages" },
                        onTraining = { selected = "Training" }
                    )
                    "Jobs" -> JobsScreen()
                    "Messages" -> MessagesScreen()
                    "Training" -> TrainingScreen()
                    else -> ProfileScreen()
                }
            }
        }
    }
}

@Composable
fun Header() {
    Row(
        Modifier.fillMaxWidth().background(Color.White).padding(horizontal = 18.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(Modifier.size(42.dp).background(Blue, CircleShape), contentAlignment = Alignment.Center) {
            Text("L", color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.width(10.dp))
        Column(Modifier.weight(1f)) {
            Text("Life OS Messenger", fontSize = 19.sp, fontWeight = FontWeight.Bold, color = Ink)
            Text("যোগাযোগ • চাকরি • দক্ষতা", fontSize = 11.sp, color = Muted)
        }
        Icon(Icons.Default.NotificationsNone, contentDescription = "Notifications", tint = Ink)
    }
}

@Composable
fun HomeScreen(onJobs: () -> Unit, onMessages: () -> Unit, onTraining: () -> Unit) {
    LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        item {
            Card(colors = CardDefaults.cardColors(containerColor = Blue), shape = RoundedCornerShape(22.dp)) {
                Column(Modifier.padding(20.dp)) {
                    Text("আপনার ভবিষ্যৎ, আপনার হাতেই", color = Color.White, fontSize = 21.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(6.dp))
                    Text("চাকরি খুঁজুন, নতুন দক্ষতা শিখুন এবং মানুষের সঙ্গে যুক্ত থাকুন।",
                        color = Color.White.copy(alpha = .9f), fontSize = 13.sp)
                    Spacer(Modifier.height(14.dp))
                    Button(onClick = onJobs, colors = ButtonDefaults.buttonColors(containerColor = Color.White)) {
                        Text("চাকরি খুঁজুন", color = Blue)
                    }
                }
            }
        }
        item { SectionTitle("আপনার প্রয়োজনীয় সেবা") }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                ServiceTile("চাকরি", Icons.Default.Work, Modifier.weight(1f), onJobs)
                ServiceTile("মেসেঞ্জার", Icons.Default.Chat, Modifier.weight(1f), onMessages)
                ServiceTile("প্রশিক্ষণ", Icons.Default.School, Modifier.weight(1f), onTraining)
            }
        }
        item { SectionTitle("নতুন সুযোগ") }
        item {
            InfoCard("জুনিয়র অফিস সহকারী", "ঢাকা • পূর্ণকালীন", "যোগ্যতা ও আবেদন প্রক্রিয়া যাচাই করে আবেদন করুন")
        }
        item {
            InfoCard("ডিজিটাল মার্কেটিং প্রশিক্ষণ", "অনলাইন • প্রাথমিক স্তর", "নতুন দক্ষতা শেখার নমুনা কোর্স")
        }
        item {
            Text("নোট: এখানে দেখানো চাকরি ও কোর্সগুলো ডেমো কনটেন্ট; বাস্তব বিজ্ঞপ্তি নয়।",
                color = Muted, fontSize = 11.sp)
        }
    }
}

@Composable
fun ServiceTile(title: String, icon: androidx.compose.ui.graphics.vector.ImageVector, modifier: Modifier, onClick: () -> Unit) {
    Card(modifier.clickable { onClick() }, colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp)) {
        Column(Modifier.fillMaxWidth().padding(vertical = 17.dp, horizontal = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(icon, contentDescription = title, tint = Blue, modifier = Modifier.size(27.dp))
            Spacer(Modifier.height(7.dp))
            Text(title, fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = Ink)
        }
    }
}

@Composable
fun SectionTitle(text: String) {
    Text(text, fontSize = 17.sp, fontWeight = FontWeight.Bold, color = Ink)
}

@Composable
fun InfoCard(title: String, subtitle: String, description: String) {
    Card(colors = CardDefaults.cardColors(containerColor = Color.White), shape = RoundedCornerShape(16.dp)) {
        Column(Modifier.fillMaxWidth().padding(16.dp)) {
            Text(title, fontWeight = FontWeight.Bold, color = Ink)
            Text(subtitle, color = Blue, fontSize = 12.sp)
            Spacer(Modifier.height(6.dp))
            Text(description, color = Muted, fontSize = 12.sp)
            Spacer(Modifier.height(8.dp))
            OutlinedButton(onClick = {}, enabled = false) { Text("বিস্তারিত (ডেমো)") }
        }
    }
}

@Composable
fun JobsScreen() {
    val jobs = listOf(
        "অফিস সহকারী" to "ময়মনসিংহ • পূর্ণকালীন",
        "সেলস প্রতিনিধি" to "ঢাকা • অভিজ্ঞতা ছাড়াও আবেদন",
        "ডাটা এন্ট্রি অপারেটর" to "রিমোট • কম্পিউটার দক্ষতা",
        "গ্রাফিক ডিজাইন শিক্ষানবিশ" to "অনলাইন • প্রশিক্ষণসহ"
    )
    LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item { SectionTitle("চাকরির সুযোগ") }
        item { Text("নিচের তালিকাটি নমুনা। যাচাইকৃত নিয়োগ বিজ্ঞপ্তি যুক্ত হলে এখানে প্রকাশ হবে।", color = Muted, fontSize = 12.sp) }
        items(jobs) { (title, place) -> InfoCard(title, place, "নিয়োগদাতার তথ্য ও আবেদনের শেষ তারিখ যাচাই করে আবেদন করুন।") }
    }
}

@Composable
fun MessagesScreen() {
    var message by remember { mutableStateOf("") }
    var sent by remember { mutableStateOf(listOf<String>()) }
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        SectionTitle("মেসেঞ্জার")
        Spacer(Modifier.height(8.dp))
        Card(colors = CardDefaults.cardColors(containerColor = Color.White)) {
            Column(Modifier.padding(14.dp)) {
                Text("চ্যাট ও কলের প্রস্তুতিমূলক স্ক্রিন", fontWeight = FontWeight.Bold, color = Ink)
                Text("বাস্তব মেসেজ, ফাইল পাঠানো ও ভিডিও কল চালু করতে সার্ভার এবং নিরাপদ কলিং সেবা সংযোগ প্রয়োজন।",
                    color = Muted, fontSize = 12.sp)
                Spacer(Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedButton(onClick = {}, enabled = false) { Icon(Icons.Default.Call, null); Text(" অডিও") }
                    OutlinedButton(onClick = {}, enabled = false) { Icon(Icons.Default.Videocam, null); Text(" ভিডিও") }
                    OutlinedButton(onClick = {}, enabled = false) { Icon(Icons.Default.AttachFile, null); Text(" ফাইল") }
                }
            }
        }
        Spacer(Modifier.height(12.dp))
        sent.forEach { Text("আপনি: $it", modifier = Modifier.padding(6.dp), color = Ink) }
        Spacer(Modifier.weight(1f))
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(value = message, onValueChange = { message = it },
                modifier = Modifier.weight(1f), placeholder = { Text("মেসেজ লিখুন") }, singleLine = true)
            IconButton(onClick = { if (message.isNotBlank()) { sent = sent + message; message = "" } }) {
                Icon(Icons.Default.Send, contentDescription = "Send", tint = Blue)
            }
        }
        Text("এই ডেমোতে মেসেজ শুধু এই স্ক্রিনে দেখা যায়; অন্য ব্যবহারকারীর কাছে যায় না।",
            color = Muted, fontSize = 10.sp)
    }
}

@Composable
fun TrainingScreen() {
    LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item { SectionTitle("দক্ষতা ও প্রশিক্ষণ") }
        item { InfoCard("কম্পিউটার বেসিক", "শুরুর স্তর • বাংলা", "কম্পিউটার ব্যবহারের প্রাথমিক ধারণা।") }
        item { InfoCard("ইংরেজি যোগাযোগ", "শুরুর স্তর • অনলাইন", "কর্মক্ষেত্রে প্রয়োজনীয় ইংরেজি অনুশীলন।") }
        item { InfoCard("ফ্রিল্যান্সিং পরিচিতি", "শুরুর স্তর • অনলাইন", "অনলাইন কাজের ধরন ও নিরাপত্তা সম্পর্কে জানুন।") }
    }
}

@Composable
fun ProfileScreen() {
    Column(Modifier.fillMaxSize().padding(18.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(Modifier.size(82.dp).background(Blue, CircleShape), contentAlignment = Alignment.Center) {
            Icon(Icons.Default.Person, contentDescription = null, tint = Color.White, modifier = Modifier.size(44.dp))
        }
        Spacer(Modifier.height(12.dp))
        Text("আপনার প্রোফাইল", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Ink)
        Text("লগইন ও পরিচয় যাচাই সুবিধা পরবর্তী সংস্করণে যুক্ত হবে।", color = Muted, fontSize = 12.sp)
        Spacer(Modifier.height(18.dp))
        listOf("ব্যক্তিগত তথ্য", "শিক্ষাগত যোগ্যতা", "দক্ষতা ও অভিজ্ঞতা", "আমার আবেদন", "সেটিংস", "সহায়তা").forEach {
            Card(Modifier.fillMaxWidth().padding(vertical = 4.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)) {
                Row(Modifier.padding(15.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text(it, modifier = Modifier.weight(1f), color = Ink)
                    Icon(Icons.Default.ChevronRight, null, tint = Muted)
                }
            }
        }
    }
}
