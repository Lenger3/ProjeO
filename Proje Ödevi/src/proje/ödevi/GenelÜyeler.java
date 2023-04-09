/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proje.ödevi;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author tahalenger
 */
public class GenelÜyeler {
    private String isim;
    private String soyisim;
    private String email;

    public GenelÜyeler(String isim, String soyisim, String email) {
        this.isim = isim;
        this.soyisim = soyisim;
        this.email = email;
    }

    public String getIsim() {
        return isim;
    }

    public String getSoyisim() {
        return soyisim;
    }

    public String getEmail() {
        return email;
    }
    
    public void DosyaYaz() throws IOException {
        BufferedWriter bw =new BufferedWriter(new FileWriter("üyeler.txt",true));
        bw.write("Genel Üyeler"+isim+"  "+soyisim+"    "+email+"\n");
        bw.close();
    }
}
