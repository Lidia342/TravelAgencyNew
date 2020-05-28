package sample.Model;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import sample.Database.BookingQueries;

import java.io.FileOutputStream;

public class PdfFile {

    public void createPdfFile(){
        /*String bookingID, String firstName, String lastName, String packageName, String bookingDate,
                String price, String departureDate, String returnDate, String departureCity, String arrivalCity,
                String flightName, String hotelName, String numOfNights, String typeOfRoom, String carType*/


        //Document document = new Document();



        /*Font blueFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, new CMYKColor(255, 0, 0, 0));
        Font redFont = FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD, new CMYKColor(0, 255, 0, 0));
        Font yellowFont = FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new CMYKColor(0, 0, 255, 0));
        Document document = new Document();
        BookingQueries bookingQueries = new BookingQueries();
        try
        {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Booking Information.pdf"));
            document.open();
            //document.add(new Paragraph("Styling Example"));

            //Paragraph with color and font styles
            Paragraph paragraphOne = new Paragraph("Thank you for booking with us", redFont);
            document.add(paragraphOne);

            //Create chapter and sections
            Paragraph chapterTitle = new Paragraph("Your Booking Information", yellowFont);
            Chapter chapter1 = new Chapter(chapterTitle, 1);
            chapter1.setNumberDepth(0);
            document.add(chapterTitle);
            bookingQueries.customerBookingInfo();
            for (int i = 0; i <bookingQueries.getCusBookingInfo().size(); i++) {
               // bookingQueries.customerBookingInfo("200213042134");

                Paragraph sectionTitle1 = new Paragraph(bookingQueries.getCusBookingInfo().get(i).getBookingID(), blueFont);
                Paragraph sectionTitle2 = new Paragraph(bookingQueries.getCusBookingInfo().get(i).getFirstName(), blueFont);
                Paragraph sectionTitle3 = new Paragraph(bookingQueries.getCusBookingInfo().get(i).getLastName(),blueFont);
                Paragraph sectionTitle4 = new Paragraph(bookingQueries.getCusBookingInfo().get(i).getPackageName(),blueFont);
                Paragraph sectionTitle5 = new Paragraph(bookingQueries.getCusBookingInfo().get(i).getBookingDate(),blueFont);
                Paragraph sectionTitle6 = new Paragraph(bookingQueries.getCusBookingInfo().get(i).getPrice(),blueFont);
                Paragraph sectionTitle7 = new Paragraph(bookingQueries.getCusBookingInfo().get(i).getDepartureDate(),blueFont);
                Paragraph sectionTitle8 = new Paragraph(bookingQueries.getCusBookingInfo().get(i).getReturnDate(),blueFont);
                Paragraph sectionTitle9 = new Paragraph(bookingQueries.getCusBookingInfo().get(i).getDepartureCity(),blueFont);
                Paragraph sectionTitle10 = new Paragraph(bookingQueries.getCusBookingInfo().get(i).getArrivalCity(), blueFont);
                Paragraph sectionTitle11 = new Paragraph(bookingQueries.getCusBookingInfo().get(i).getFlightName(), blueFont);
                Paragraph sectionTitle12 = new Paragraph(bookingQueries.getCusBookingInfo().get(i).getHotelName(), blueFont);
                Paragraph sectionTitle13 = new Paragraph(bookingQueries.getCusBookingInfo().get(i).getNumOfNights(),blueFont);
                Paragraph sectionTitle14 = new Paragraph(bookingQueries.getCusBookingInfo().get(i).getTypeOfRoom(),blueFont);
                Paragraph sectionTitle15 = new Paragraph(bookingQueries.getCusBookingInfo().get(i).getCarType(),blueFont);


                chapter1.addSection(sectionTitle1);
                chapter1.addSection(sectionTitle2);
                chapter1.addSection(sectionTitle3);
                chapter1.addSection(sectionTitle4);
                chapter1.addSection(sectionTitle5);
                chapter1.addSection(sectionTitle6);
                chapter1.addSection(sectionTitle7);
                chapter1.addSection(sectionTitle8);
                chapter1.addSection(sectionTitle9);
                chapter1.addSection(sectionTitle10);
                chapter1.addSection(sectionTitle11);
                chapter1.addSection(sectionTitle12);
                chapter1.addSection(sectionTitle13);
                chapter1.addSection(sectionTitle14);
                chapter1.addSection(sectionTitle15);
                document.add(chapter1);

            }





            //Paragraph sectionContent = new Paragraph("Section Text content", blueFont);
            //section1.add(sectionContent);


            document.close();
            writer.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

         */






        /*try{
            PdfWriter writer= PdfWriter.getInstance(document,new FileOutputStream("Booking Information.pdf"));
            document.open();
            BookingQueries bookingQueries = new BookingQueries();

            document.add(new Paragraph("Booking Information"));
            List orderedList = new List(List.ORDERED);
            orderedList.add(new ListItem(bookingID));
            orderedList.add(new ListItem(firstName));
            orderedList.add(new ListItem(lastName));
            orderedList.add(new ListItem(packageName));
            orderedList.add(new ListItem(bookingDate));
            orderedList.add(new ListItem(price));
            orderedList.add(new ListItem(departureDate));
            orderedList.add(new ListItem(returnDate));
            orderedList.add(new ListItem(departureCity));
            orderedList.add(new ListItem(arrivalCity));
            orderedList.add(new ListItem(flightName));
            orderedList.add(new ListItem(hotelName));
            orderedList.add(new ListItem(numOfNights));
            orderedList.add(new ListItem(typeOfRoom));
            orderedList.add(new ListItem(carType));

            document.add(orderedList);

            document.close();
            writer.close();

        }catch(FileNotFoundException | DocumentException ex){
            System.out.println(ex.getMessage());
        }


         */

        /*Font redFont = FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD, new CMYKColor(0, 255, 0, 0));
        Document document = new Document();
        BookingQueries bookingQueries = new BookingQueries();
        try
        {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Booking Inf.pdf"));
            document.open();

            Paragraph p = new Paragraph();
            p.add(new Paragraph("Thank you for booking with us",redFont));
            document.add(new Paragraph("Your Booking Information",redFont));
            bookingQueries.customerBookingInfo();
            for (int i = 0; i <bookingQueries.getCusBookingInfo().size(); i++) {
                document.add(new Paragraph("Booking ID",redFont));
                document.add(new Paragraph(bookingQueries.getCusBookingInfo().get(i).getBookingID(), redFont));
                document.add(new Paragraph(bookingQueries.getCusBookingInfo().get(i).getName(), redFont));
                document.add(new Paragraph(bookingQueries.getCusBookingInfo().get(i).getPackageName(), redFont));
                document.add(new Paragraph(bookingQueries.getCusBookingInfo().get(i).getBookingDate(), redFont));
                document.add(new Paragraph(bookingQueries.getCusBookingInfo().get(i).getPrice(), redFont));
                document.add(new Paragraph(bookingQueries.getCusBookingInfo().get(i).getDepartureDate(), redFont));
                document.add(new Paragraph(bookingQueries.getCusBookingInfo().get(i).getDepartureCity(), redFont));
                document.add(new Paragraph(bookingQueries.getCusBookingInfo().get(i).getArrivalCity(), redFont));
                document.add(new Paragraph(bookingQueries.getCusBookingInfo().get(i).getFlightName(), redFont));
                document.add(new Paragraph(bookingQueries.getCusBookingInfo().get(i).getHotelName(), redFont));
                document.add(new Paragraph(bookingQueries.getCusBookingInfo().get(i).getNumOfNights(), redFont));
                document.add(new Paragraph(bookingQueries.getCusBookingInfo().get(i).getTypeOfRoom(), redFont));
                document.add(new Paragraph(bookingQueries.getCusBookingInfo().get(i).getCarType(), redFont));



            }
            document.close();
            writer.close();
        } catch (DocumentException e)
        {
            e.printStackTrace();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

         */

        try {
            BookingQueries bookingQueries = new BookingQueries();
            Document document = new Document();
            PdfWriter writer= PdfWriter.getInstance(document,new FileOutputStream("Booking Information.pdf"));
            document.open();

            Paragraph p = new Paragraph();
            p.add("\n");
            p.add(" Your   Booking   Information");
            p.add("\n ==========================================================");

            bookingQueries.customerBookingInfo();
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


