import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;





public class DocumentPrintable implements Printable {
        private String textForPrinting;
        private String arrayRows [];
        private int[] pageBreaks;
        private String textPageNumber = " ";
        private String textNameTeam = "Page";
    public  DocumentPrintable(String textForPrinting){
        this.textForPrinting = textForPrinting;
        arrayRows = textForPrinting.split("\n");
    }

    @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {

        int y = 50;
        int x = 50;
        Font font = new Font("Helvetica", Font.PLAIN, 8);
        FontMetrics metrics = g.getFontMetrics(font);
        int lineHeight = metrics.getHeight();

        int paddingWidthPage = x * 2;
        int pageWidth = (int) (pf.getImageableWidth()) - paddingWidthPage;
        int pageHeight = (int) pf.getImageableHeight() - (y * 2);

        if (pageBreaks == null) {
//            initTextLines();
            int linesPerPage = pageHeight / lineHeight;
            int numBreaks = (arrayRows.length-1)/linesPerPage;
            pageBreaks = new int[numBreaks];
            for (int b=0; b<numBreaks; b++) {
                pageBreaks[b] = (b+1)*linesPerPage;
            }
        }
        if (page > pageBreaks.length) {
            return NO_SUCH_PAGE;
        }
        int pageNumberX = (int) pf.getImageableWidth() - metrics.stringWidth(textPageNumber) - x;
        int pageNumberY = (int) pf.getImageableHeight() - y / 2;



        //print team name
        g.setColor(Color.BLUE);
        g.setFont(font);
        g.drawString(textNameTeam, x, pageNumberY);

        //print page number
        g.setColor(Color.BLUE);
        g.setFont(font);
        g.drawString(textPageNumber + (page + 1), pageNumberX, pageNumberY);

        //print text lines

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);

        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         * Since we are drawing text we
         */
//        Graphics2D g2d = (Graphics2D)g;
//        g2d.translate(pf.getImageableX(), pf.getImageableY());

        /* Draw each line that is on this page.
         * Increment 'y' position by lineHeight for each line.
         */

        int start = (page == 0) ? 0 : pageBreaks[page-1];
        int end   = (page == pageBreaks.length)
                ? arrayRows.length : pageBreaks[page];
        for (int line=start; line<end; line++) {
            y += lineHeight;
            g.drawString(arrayRows[line], x, y);
        }
        return PAGE_EXISTS;
    }

}
