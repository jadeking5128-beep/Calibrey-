# Calibrey: Complete APK Installation & Deployment Guide

This guide explains step-by-step how to generate, download, and install the **Calibrey** Android Application Package (APK) onto any physical Android smartphone, tablet, or emulator.

---

## 📋 System Requirements
- **Android OS**: Android 7.0 (API Level 24 - Nougat) or higher (Android 10, 11, 12, 13, 14, 15 fully supported).
- **Available Storage**: Minimum 100 MB free storage on device.
- **Architectures**: ARM64 (`arm64-v8a`), ARM32 (`armeabi-v7a`), and x86_64 supported out-of-the-box.

---

## 🛠️ Method 1: Exporting Directly from Google AI Studio (Fastest)

If you are using Google AI Studio Build:

1. Look at the top right toolbar of the AI Studio interface.
2. Click the **Project Settings** or **Export / Download** button.
3. Select **"Download APK"** or **"Generate APK"**.
4. The cloud build server will package the project and prompt your browser to download `Calibrey.apk`.
5. Proceed to [Installing the APK on Your Physical Phone](#-installing-the-apk-on-your-physical-phone).

---

## 💻 Method 2: Building the APK Locally with Gradle

If you have cloned this repository to your local machine:

### 1. Ensure Prerequisites
- Make sure you have **JDK 17** or **JDK 21** installed.
- Ensure Android SDK environment variables are set:
  ```bash
  export ANDROID_HOME=$HOME/Android/Sdk
  export PATH=$PATH:$ANDROID_HOME/platform-tools
  ```

### 2. Configure Environment Secret
Copy `.env.example` to `.env` in the repository root:
```bash
cp .env.example .env
```
Add your Google Gemini API key:
```ini
GEMINI_API_KEY=your_gemini_api_key_here
```

### 3. Assemble the Debug APK
Run the Gradle assemble command:

```bash
# On Linux / macOS:
gradle assembleDebug

# Or using the Gradle wrapper if configured:
./gradlew assembleDebug

# On Windows PowerShell:
gradle.bat assembleDebug
```

Once the build finishes (`BUILD SUCCESSFUL`), the output APK file will be located at:
```
app/build/outputs/apk/debug/app-debug.apk
```

---

## 📲 Method 3: Installing via USB Cable & ADB (For Developers)

If your phone is connected to your computer via USB:

1. Enable **Developer Options** on your phone:
   - Go to **Settings** $\rightarrow$ **About phone**.
   - Tap **Build number** 7 times until you see *"You are now a developer!"*.
2. Enable **USB Debugging**:
   - Go to **Settings** $\rightarrow$ **System** $\rightarrow$ **Developer options**.
   - Enable the toggle for **USB debugging**.
3. Plug your phone into your computer with a USB cable. Tap **"Allow"** when prompted on your phone screen.
4. Verify the device connection:
   ```bash
   adb devices
   ```
5. Install directly:
   ```bash
   adb install -r app/build/outputs/apk/debug/app-debug.apk
   ```
6. The app will immediately appear on your phone's app drawer under the name **Calibrey**!

---

## 📲 Installing the APK on Your Physical Phone (Sideloading)

If you downloaded or transferred `app-debug.apk` directly to your phone:

### Step 1: Transfer File to Your Phone
- **Via USB Cable**: Connect your phone to your PC, choose "File Transfer / MTP", and drag `app-debug.apk` into your phone's **Downloads** folder.
- **Via Cloud**: Upload `app-debug.apk` to your personal Google Drive, OneDrive, or Telegram Saved Messages, then download it on your phone.
- **Via Browser**: Directly download the APK via the exported link.

### Step 2: Open File Manager
1. Open the default **Files** or **File Manager** app on your Android device.
2. Navigate to the **Downloads** folder.
3. Locate `app-debug.apk` (or `Calibrey.apk`).

### Step 3: Grant "Install Unknown Apps" Permission
Because this APK is built directly from source code and not downloaded from the Google Play Store, Android security will ask for confirmation:
1. Tap the APK file.
2. A system prompt will appear: *"For your security, your phone is not allowed to install unknown apps from this source"*.
3. Tap **Settings**.
4. Toggle **"Allow from this source"** to ON.
5. Tap the back button to return to the installation dialog.

### Step 4: Confirm Installation
1. Tap **Install**.
2. If Google Play Protect displays a prompt stating *"Unrecognized App / Blocked by Play Protect"* (standard for all custom development builds):
   - Tap **"More details"**.
   - Tap **"Install anyway"**.
3. Once the installation is finished, tap **Open**.

---

## ⚙️ Initial App Setup & First Launch

1. **Authentication Portal**:
   - On initial launch, you will be greeted by the **Calibrey Academic Authentication Portal**.
   - Enter your **Full Name** and select your role (**Student** or **Educator**).
   - Select your target NCERT grade:
     - **Class 9** (Science & Mathematics)
     - **Class 10** (Science & Mathematics)
     - **Class 11** (Physics, Chemistry, Biology, Mathematics)
     - **Class 12** (Physics, Chemistry, Biology, Mathematics)
   - Choose your academic insignia icon (`SC`, `MT`, `PH`, `CH`, `BI`, `CB`).
2. **AI Tutor Activation**:
   - If you provided a `GEMINI_API_KEY` in `.env`, the AI tutor is pre-activated!
   - If not, you can tap on your profile in the top-right corner $\rightarrow$ Enter your Gemini API key $\rightarrow$ Tap Save.
3. **Start Learning**:
   - Access the **Curriculum** tab to browse textbook chapters and subtopics.
   - Use the **Knowledge Graph** to inspect conceptual dependencies.
   - Enter the **Exam War Room** to set focus sprint timers.

---

## ❓ Troubleshooting FAQs

| Symptom | Cause | Solution |
| :--- | :--- | :--- |
| **"App not installed" / Package corrupt** | Conflicting signature with previous build or insufficient storage | Uninstall any existing version of Calibrey first. Ensure at least 150 MB of free storage. |
| **"Play Protect blocked installation"** | Development debug certificate used | Tap *"More details"* $\rightarrow$ *"Install anyway"*. This is normal for development APKs. |
| **"Parse error"** | Android version is older than Android 7.0 (API 24) | Upgrade device to Android 7.0+ or test on a newer device/emulator. |
| **AI Tutor returns network error** | Missing Gemini API key or device offline | Check your internet connectivity and ensure a valid Gemini API key is entered in Profile Settings. |
