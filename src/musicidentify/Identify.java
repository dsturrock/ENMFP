package musicidentify;

public class Identify {
	
	String code = "";
	//Call the music identification services URL and get the metadata for this song
	String ENMFPUrl = "http://developer.echonest.com/api/v4/song/identify?api_key=77ENISIQZSEQKAUFV&code="+code;
}
