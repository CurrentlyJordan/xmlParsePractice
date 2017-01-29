package nyc.c4q.jordansmith.practicexmlparser;

import nyc.c4q.jordansmith.practicexmlparser.Model.Rss;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by jordansmith on 1/26/17.
 */

public interface ParksService {

    @GET("xml/events_300_rss.xml")
    Call<Rss> getParkEvents();

}
