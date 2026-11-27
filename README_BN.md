# Life OS Messenger — Android MVP

এটি Life OS Messenger-এর একটি প্রাথমিক Android প্রজেক্ট (Kotlin + Jetpack Compose)।

## এই সংস্করণে আছে
- Home, Jobs, Messages, Training, Profile স্ক্রিন
- নমুনা চাকরি ও প্রশিক্ষণের তালিকা
- ডেমো মেসেজ ইনপুট (শুধু ডিভাইসের স্ক্রিনে স্থানীয়ভাবে দেখায়)
- ভিডিও কল/অডিও কল/ফাইল সংযুক্তির UI placeholder

## এখনো চালু নয়
বাস্তব ব্যবহারকারী অ্যাকাউন্ট, OTP, সার্ভার-ভিত্তিক মেসেজিং, ফাইল আপলোড/ডাউনলোড,
HD ভিডিও/অডিও কল, গ্রুপ কল, চাকরির বাস্তব ডেটা, পেমেন্ট এবং অ্যাডমিন প্যানেল।
এসবের জন্য backend, database, authentication, storage, WebRTC/SFU calling
infrastructure, moderation ও privacy/security review প্রয়োজন।

## Android Studio দিয়ে চালানোর নিয়ম
1. Android Studio-তে এই ZIP extract করে `LifeOSMessenger_Android` ফোল্ডার Open করুন।
2. Gradle sync সম্পন্ন হতে দিন (ইন্টারনেট লাগবে)।
3. Android SDK 35 এবং JDK 17 ব্যবহার করুন।
4. Emulator বা Android ফোনে Run চাপুন.

## গুরুত্বপূর্ণ
এটি production-ready অ্যাপ নয় এবং কোনো চাকরি/প্রশিক্ষণ বাস্তবে যাচাই করা হয়নি।
সবার জন্য ফ্রি রাখার নীতি বজায় রাখতে সার্ভার, স্টোরেজ, ভিডিও কল ব্যান্ডউইথ,
SMS OTP ও রক্ষণাবেক্ষণের খরচের জন্য আলাদা অর্থায়ন পরিকল্পনা লাগবে।
