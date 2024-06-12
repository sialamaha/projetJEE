package authentification;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import authentificationrole.models.DBUtil;

@WebServlet("/DownloadPDF")
public class DownloadPDFServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DownloadPDFServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the demand ID from the request
        int demandeId = 5;
        
        // Use the ID to retrieve the corresponding PDF from the database
        byte[] pdfData = retrievePDFFromDatabase(demandeId);
        
        if (pdfData != null) {
            // Configure response headers for downloading PDF file
            response.setContentType("application/pdf");
            response.setContentLength(pdfData.length);
            response.setHeader("Content-Disposition", "attachment; filename=\"demande.pdf\"");
            
            // Write PDF data to the response
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(pdfData);
            outputStream.close();
        } else {
            // Handle case where PDF data is not available
            response.getWriter().write("No PDF file found for the provided ID.");
        }
    }
    
    // Method to retrieve PDF from the database
    private byte[] retrievePDFFromDatabase(int demandeId) {
        String query = "SELECT document_PDF FROM demandetirage WHERE idtirage = ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             
            preparedStatement.setInt(1, demandeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                return resultSet.getBytes("document_PDF");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle SQL exceptions
        }
        return null;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
