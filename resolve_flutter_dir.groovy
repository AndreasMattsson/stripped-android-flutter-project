def resolveWithDoctor() {
    def isWindows = org.gradle.internal.os.OperatingSystem.current().isWindows()
    try {
        (( new ProcessBuilder("flutter${isWindows ? ".bat" : ""}", "doctor", "-v")
                .start()
                .inputStream
                .text
        ) =~ /Flutter version .*at (.*)/)[0][1]
    } catch(_) {
        ""
    }
}

def resolveWithLocalProperties(File localPropertiesFile) {
    def localProperties = new Properties()
    while(!localPropertiesFile.exists() && localPropertiesFile.parentFile != rootProject.projectDir) {
        localPropertiesFile = new File(localPropertiesFile.parentFile.parentFile, 'local.properties')
    }
    if (localPropertiesFile.exists()) {
        localPropertiesFile.withReader('UTF-8') { reader ->
            localProperties.load(reader)
        }
    }
    return localProperties.getProperty('flutter.sdk')
}

static def resolveWithEnv() {
    def fromEnv = System.getenv('FLUTTER_ROOT') ?: ''
    if (new File(fromEnv).exists()) {
        return fromEnv
    } else {
        return null
    }
}

def flutterLocalPropertiesFile = new File(
        project(':flutter').projectDir.parentFile,
        'local.properties'
)
def flutterLocalPropertiesRoot = resolveWithLocalProperties(flutterLocalPropertiesFile)
def flutterRoot = flutterLocalPropertiesRoot ?: resolveWithEnv() ?: resolveWithDoctor()
gradle.ext.flutterRoot = flutterRoot
if (flutterRoot != null && flutterLocalPropertiesRoot == null) {
    def props = new Properties()
    if (flutterLocalPropertiesFile.exists()) {
        props.load(flutterLocalPropertiesFile.newInputStream())
    } else {
        flutterLocalPropertiesFile.createNewFile()
    }
    props.setProperty('flutter.sdk', flutterRoot)
    def writer = flutterLocalPropertiesFile.newWriter()
    props.store(writer, null)
    writer.close()
}