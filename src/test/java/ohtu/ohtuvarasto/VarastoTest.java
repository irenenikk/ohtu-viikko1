package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void konstruktorissaEiVoiAntaaNegatiivistTilavuutta() {
        Varasto uusiVarasto = new Varasto(-1);
        assertEquals( 0,uusiVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktorissaEiVoiAntaaNegatiivistaAlkusaldoa() {
        Varasto uusiVarastp = new Varasto(10, -1);
        assertEquals(0, uusiVarastp.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void josAlkuSaldoSuurempiKuinTilavuusVarastoAsetetaanTayteen() {
        Varasto uusiVarasto = new Varasto(10, 20);
        assertEquals(10, uusiVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void josAlkusaldoPienempiKuinTilavuusAlkusaldoAsetetaan() {
        Varasto uusiVarasto = new Varasto(10, 5);
        assertEquals(5, uusiVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastoonEiVoiLisataNegatiivista() {
        varasto.lisaaVarastoon(5);
        varasto.lisaaVarastoon(-1);
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastostaEiVoiOttaaNegatiivista() {
        double tulos = varasto.otaVarastosta(-1);
        assertEquals(0, tulos, vertailuTarkkuus);
    }

    @Test
    public void josOtetaanEnemmanKuinOnAnnetaanKaikki() {
        varasto.lisaaVarastoon(2);
        double annettu = varasto.otaVarastosta(5);
        assertEquals(2, annettu, vertailuTarkkuus);
    }

    @Test
    public void toStringToimiiOikein() {
        String toS = varasto.toString();
        assertEquals(toS, "saldo = 0.0, vielä tilaa 10.0");
    }

    @Test
    public void josVarastoonLisataanEnemmanKuinMitaMahtuuLaitetaanTayteen() {
        varasto.lisaaVarastoon(100);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void tilavuusEiVoiOllaNegatiivinenVaikkaSaldoOlisiOk() {
        Varasto uusiVarasto = new Varasto(-1, 1);
        assertEquals(0, uusiVarasto.getTilavuus(), vertailuTarkkuus);
    }
}