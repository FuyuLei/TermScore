package comn.example.user.termscore.retrofit;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface TermScoreService {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://140.131.110.76/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build();

    TermScoreService service = TermScoreService.retrofit.create(TermScoreService.class);

    @Headers("X-Requested-With:com.hanglong.NTUBStdApp")
    @FormUrlEncoded
    @POST("JMobile_STD/AjaxPage/SRHGRD_Years_ajax.aspx")
    Call<String> Data(@Field("strYears") String strYears, @Field("strTerm") String strTerm
           ,@Field("StdNo") String StdNo, @Field("Screen_Width") String Screen_Width, @Field("flag") String flag);
}
