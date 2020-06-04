package nl.hanze.hanzeboard.activities.overview.announcements;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import nl.hanze.hanzeboard.api.API;
import nl.hanze.hanzeboard.api.clients.AnnouncementClient;
import nl.hanze.hanzeboard.api.responses.announcement.AnnouncementMessageResponse;
import nl.hanze.hanzeboard.api.responses.course.CourseResponse;
import nl.hanze.hanzeboard.api.responses.staff.StaffMessageResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnnouncementsViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private AnnouncementClient announcementClient;
    private MutableLiveData<AnnouncementMessageResponse> mAnnouncementsData = new MutableLiveData<>();

    private Announcement[] announcements = {
            new Announcement("Alweer een inzage verplaatst!", "Docent", "12 December 2020 op 14:59", "Ja het gebeurt wel eens, een inzage wordt verplaatst. Sucks to be you, dit ook het geval bij het vak OOP5 Quantum computing with applied linear regression. Tja sorry voor 96.5% die dit vak niet heeft gehaald."),
            new Announcement("Inzage moment OOP5", "Docent", "12 December 2020 op 14:57", "Het inzage moment voor OOP5 staat ingepland op 1 Juno. Maar dit is niet 100% zeker, verder kan door de corona crisis voor deze toets geen 6de kans komen. Sucks to be you."),
            new Announcement("Update Corona", "Docent", "12 December 2020 op 14:55",
                    "Het kabinet heeft gisteren bekend gemaakt dat wij als hogeschool vanaf 15 juni in beperkte mate praktijkonderwijs en toetsing op locatie mogen verrichten. Dit betekent wel extra druk op het openbaar vervoer en we willen voorkomen dat er onveilige situaties ontstaan. Daarover hebben we intensief contact met de Veiligheidsregio Groningen en we informeren je op een later moment over het gebruik van het openbaar vervoer van en naar de Hanzehogeschool. Tot die tijd vragen we je zoveel mogelijk gebruik te maken van de fiets. \u200B\n" +
                    "\n" +
                    "Stap voor stap naar een gedeeltelijke openstelling: tot 15 juni en daarna\u200B\n" +
                    "De afgelopen weken hebben we, binnen de ruimte die het ministerie van Onderwijs, Cultuur en Wetenschap biedt en de richtlijnen van het RIVM, een aantal faciliteiten gedeeltelijk opengesteld. Hierin maken we onderscheid tussen de periode tot 15 juni, en de periode na 15 juni. \u200B\n" +
                    "\n" +
                    "Tot 15 juni\u200B\n" +
                    "Tot 15 juni is het mogelijk om beperkt gebruik te maken van een aantal faciliteiten, zoals: \u200B\n" +
                    "- De Hanze Mediatheek;\u200B\n" +
                    "- Skills labs;\u200B\n" +
                    "- Studieplekken.\u200B\n" +
                    "\n" +
                    "Kijk op hanze.nl/corona om te zien welke faciliteiten precies open zijn, hoe je je daarvoor kunt inschrijven en voor welke faciliteiten je in aanmerking komt, bijvoorbeeld omdat je een online tentamen moet maken en thuis een slechte internetverbinding hebt, of een boek uit de mediatheek nodig hebt. \u200B\n" +
                    "\n" +
                    "Vanaf 15 juni \u200B\n" +
                    "De overheid geeft ons vanaf 15 juni wat ruimte voor praktijkonderwijs en tentamens op locatie. Wat er verder mogelijk is vanaf die datum en wat dat precies voor jou als student betekent, zijn we momenteel aan het uitwerken. Je ontvangt hierover zo spoedig mogelijk meer informatie.\u200B\n" +
                    "\n" +
                    "Tentamens\u200B\n" +
                    "Er vinden nog veel toetsen en tentamens plaats in periode 4. Deze zullen zoveel mogelijk online worden aangeboden. Vanaf 15 juni is het dus mogelijk een beperkt aantal toetsen fysiek, dus op locatie, af te nemen. Uiterlijk vijf werkdagen voordat je tentamen plaatsvindt hoor je van je opleiding in welke vorm het plaats gaat vinden. Heb je vragen over je toetsen en tentamens? Neem contact op met je opleiding. \u200B\n" +
                    "\n" +
                    "Financiële compensatie en teruggave collegegeld\u200B\n" +
                    "Het Ministerie van Onderwijs, Cultuur en Wetenschap heeft aangekondigd studenten financieel tegemoet te komen, waaronder gedeeltelijke teruggave van het collegegeld. Voor informatie over alle voorwaarden en andere financiële compensatieregelingen kun je terecht op de website van DUO.\u200B\n" +
                    "\n" +
                    "We zijn blij dat de overheid enkele verruimingen aangekondigd heeft en dat we iets meer faciliteiten gedeeltelijk kunnen openstellen. We informeren je binnenkort over hoe de periode na 15 juni er uit komt te zien. \u200B"),
            new Announcement("Resultaten OOP5", "Docent", "12 December 2020 op 14:55", "De resultaten van het vak OOP5 zijn beter dan vorig jaar! 3.5% van alle studenten heeft het dit keer gehaald! Van harte gefeliciteerd. Meer informatie over een inzagemoment volgt."),
            new Announcement("Resultaten OOP4", "Docent", "12 December 2020 op 14:55", "De resultaten van het vak OOP5 zijn beter dan vorig jaar! 3.5% van alle studenten heeft het dit keer gehaald! Van harte gefeliciteerd. Meer informatie over een inzagemoment volgt."),
            new Announcement("Resultaten OOP3", "Docent", "12 December 2020 op 14:55", "De resultaten van het vak OOP5 zijn beter dan vorig jaar! 3.5% van alle studenten heeft het dit keer gehaald! Van harte gefeliciteerd. Meer informatie over een inzagemoment volgt."),
            new Announcement("Resultaten OOP2", "Docent", "12 December 2020 op 14:55", "De resultaten van het vak OOP5 zijn beter dan vorig jaar! 3.5% van alle studenten heeft het dit keer gehaald! Van harte gefeliciteerd. Meer informatie over een inzagemoment volgt."),
    };

    public void init(Context context, List<CourseResponse> courses){
        announcementClient = API.createService(context, AnnouncementClient.class);

        Call<AnnouncementMessageResponse> courseCall = announcementClient.getAnnouncements("1");
        courseCall.enqueue(new Callback<AnnouncementMessageResponse>() {
            @Override
            public void onResponse(Call<AnnouncementMessageResponse> call, Response<AnnouncementMessageResponse> response) {
                if (response.isSuccessful()) {
                    mAnnouncementsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<AnnouncementMessageResponse> call, Throwable t) {
                Log.v("ERROR: ", t.getMessage());
            }
        });
    }

    public LiveData<AnnouncementMessageResponse> getAnnouncementsData() {
        return mAnnouncementsData;
    }
}
