package sample.Model;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import sample.Database.BookingQueries;

import java.io.FileOutputStream;

public class PdfFile {

    public void createPdfFile(String path){


        try {
            Document document = new Document();


            BookingQueries bookingQueries = new BookingQueries();
            PdfWriter writer= PdfWriter.getInstance(document,new FileOutputStream(path));
            document.open();

            Paragraph p = new Paragraph();
            p.add("\n");
            p.add(" Your   Booking   Information");
            p.add("\n ==========================================================");

            bookingQueries.customerBookingInfo(bookingQueries.getBookingId());
            for (int i = 0; i <bookingQueries.getCusBookingInfo().size(); i++){
                p.add("\n Booking Id :   "+ bookingQueries.getCusBookingInfo().get(i).getBookingID());
                p.add("\n Name :   "+ bookingQueries.getCusBookingInfo().get(i).getName());
                p.add("\n Package Name :   " + bookingQueries.getCusBookingInfo().get(i).getPackageName());
                p.add("\n Booking Date :   " + bookingQueries.getCusBookingInfo().get(i).getBookingDate());
                p.add("\n Price :   " + bookingQueries.getCusBookingInfo().get(i).getPrice());
                p.add("\n Departure Date :   " + bookingQueries.getCusBookingInfo().get(i).getDepartureDate());
                p.add("\n Return Date :   " + bookingQueries.getCusBookingInfo().get(i).getReturnDate());
                p.add("\n Departure City :   " + bookingQueries.getCusBookingInfo().get(i).getDepartureCity());
                p.add("\n Arrival City :   " + bookingQueries.getCusBookingInfo().get(i).getArrivalCity());
                p.add("\n Flight Name :   " + bookingQueries.getCusBookingInfo().get(i).getFlightName());
                p.add("\n Hotel Name :   " + bookingQueries.getCusBookingInfo().get(i).getHotelName());
                p.add("\n Number of nights :   " + bookingQueries.getCusBookingInfo().get(i).getNumOfNights());
                p.add("\n Type of room :   " + bookingQueries.getCusBookingInfo().get(i).getTypeOfRoom());
                p.add("\n Car type :   " + bookingQueries.getCusBookingInfo().get(i).getCarType());
                p.add("\n ==========================================================");}



            document.add(p);
            Image img = Image.getInstance("nyLogo.png");
            img.setAbsolutePosition(455f, 755f);
//Scale to new height and new width of image
            img.scaleAbsolute(70, 70);


            document.add(img);
            document.close();
            writer.close();

        }

        catch (Exception e) {
            System.out.println(e.getMessage());

        }


    }

}


