package examreport;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ExamRecordData {
    private  final StringProperty record_id;
    private  final StringProperty surname;
    private  final StringProperty firstName;
    private  final StringProperty Clas;
    private  final StringProperty term;
    private  final StringProperty student_id;
    private  final StringProperty subject_id;
    private  final StringProperty mathematics;
    private  final StringProperty english;
    private  final StringProperty science;
    private  final StringProperty rme;
    private  final StringProperty fante;
    private  final StringProperty s_studies;
    private  final StringProperty bdt;
    private  final StringProperty ict;
    private  final StringProperty numeracy;
    private  final StringProperty c_art;
    private  final StringProperty l_literacy;
    private  final StringProperty c_education;
    private  final StringProperty e_studies;

    private  final StringProperty mathematics_cs;
    private  final StringProperty english_cs;
    private  final StringProperty science_cs;
    private  final StringProperty rme_cs;
    private  final StringProperty fante_cs;
    private  final StringProperty s_studies_cs;
    private  final StringProperty bdt_cs;
    private  final StringProperty ict_cs;
    private  final StringProperty numeracy_cs;
    private  final StringProperty c_art_cs;
    private  final StringProperty l_literacy_cs;
    private  final StringProperty c_education_cs;
    private  final StringProperty e_studies_cs;



    public ExamRecordData(String record_id, String surname, String firstName, String clas, String term, String student_id, String mathematics,String mathematics_cs,
                          String english,String english_cs, String science,String science_cs, String rme,String rme_cs, String fante,String fante_cs, String s_studies,
                          String s_studies_cs, String bdt,String bdt_cs, String ict,String ict_cs, String numeracy,String numeracy_cs,
                          String c_art,String c_art_cs, String l_literacy,String l_literacy_cs, String c_education,String c_education_cs, String e_studies,String e_studies_cs, String subject_id) {
        this.record_id = new SimpleStringProperty(record_id);
        this.surname = new SimpleStringProperty(surname);
        this.firstName = new SimpleStringProperty(firstName);
        Clas = new SimpleStringProperty(clas);
        this.term = new SimpleStringProperty(term);
        this.student_id = new SimpleStringProperty(student_id);
        this.subject_id = new SimpleStringProperty(subject_id);
        this.mathematics = new SimpleStringProperty(mathematics);
        this.english = new SimpleStringProperty(english);
        this.science = new SimpleStringProperty(science);
        this.rme = new SimpleStringProperty(rme);
        this.fante = new SimpleStringProperty(fante);
        this.s_studies = new SimpleStringProperty(s_studies);
        this.bdt = new SimpleStringProperty(bdt);
        this.ict = new SimpleStringProperty(ict);
        this.numeracy = new SimpleStringProperty(numeracy);
        this.c_art = new SimpleStringProperty(c_art);
        this.l_literacy = new SimpleStringProperty(l_literacy);
        this.c_education = new SimpleStringProperty(c_education);
        this.e_studies = new SimpleStringProperty(e_studies);
        this.mathematics_cs = new SimpleStringProperty(mathematics_cs);
        this.english_cs = new SimpleStringProperty(english_cs);
        this.science_cs = new SimpleStringProperty(science_cs);
        this.rme_cs = new SimpleStringProperty(rme_cs);
        this.fante_cs = new SimpleStringProperty(fante_cs);
        this.s_studies_cs = new SimpleStringProperty(s_studies_cs);
        this.bdt_cs = new SimpleStringProperty(bdt_cs);
        this.ict_cs = new SimpleStringProperty(ict_cs);
        this.numeracy_cs = new SimpleStringProperty(numeracy_cs);
        this.c_art_cs = new SimpleStringProperty(c_art_cs);
        this.l_literacy_cs = new SimpleStringProperty(l_literacy_cs);
        this.c_education_cs = new SimpleStringProperty(c_education_cs);
        this.e_studies_cs = new SimpleStringProperty(e_studies_cs);
    }

    public String getRecord_id() {
        return record_id.get();
    }

    public StringProperty record_idProperty() {
        return record_id;
    }

    public void setRecord_id(String record_id) {
        this.record_id.set(record_id);
    }

    public String getSurname() {
        return surname.get();
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getClas() {
        return Clas.get();
    }

    public StringProperty clasProperty() {
        return Clas;
    }

    public void setClas(String clas) {
        this.Clas.set(clas);
    }

    public String getTerm() {
        return term.get();
    }

    public StringProperty termProperty() {
        return term;
    }

    public void setTerm(String term) {
        this.term.set(term);
    }

    public String getStudent_id() {
        return student_id.get();
    }

    public StringProperty student_idProperty() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id.set(student_id);
    }

    public String getSubject_id() {
        return subject_id.get();
    }

    public StringProperty subject_idProperty() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id.set(subject_id);
    }

    public String getMathematics() {
        return mathematics.get();
    }

    public StringProperty mathematicsProperty() {
        return mathematics;
    }

    public void setMathematics(String mathematics) {
        this.mathematics.set(mathematics);
    }

    public String getEnglish() {
        return english.get();
    }

    public StringProperty englishProperty() {
        return english;
    }

    public void setEnglish(String english) {
        this.english.set(english);
    }

    public String getScience() {
        return science.get();
    }

    public StringProperty scienceProperty() {
        return science;
    }

    public void setScience(String science) {
        this.science.set(science);
    }

    public String getRme() {
        return rme.get();
    }

    public StringProperty rmeProperty() {
        return rme;
    }

    public void setRme(String rme) {
        this.rme.set(rme);
    }

    public String getFante() {
        return fante.get();
    }

    public StringProperty fanteProperty() {
        return fante;
    }

    public void setFante(String fante) {
        this.fante.set(fante);
    }

    public String getS_studies() {
        return s_studies.get();
    }

    public StringProperty s_studiesProperty() {
        return s_studies;
    }

    public void setS_studies(String s_studies) {
        this.s_studies.set(s_studies);
    }

    public String getBdt() {
        return bdt.get();
    }

    public StringProperty bdtProperty() {
        return bdt;
    }

    public void setBdt(String bdt) {
        this.bdt.set(bdt);
    }

    public String getIct() {
        return ict.get();
    }

    public StringProperty ictProperty() {
        return ict;
    }

    public void setIct(String ict) {
        this.ict.set(ict);
    }

    public String getNumeracy() {
        return numeracy.get();
    }

    public StringProperty numeracyProperty() {
        return numeracy;
    }

    public void setNumeracy(String numeracy) {
        this.numeracy.set(numeracy);
    }

    public String getC_art() {
        return c_art.get();
    }

    public StringProperty c_artProperty() {
        return c_art;
    }

    public void setC_art(String c_art) {
        this.c_art.set(c_art);
    }

    public String getL_literacy() {
        return l_literacy.get();
    }

    public StringProperty l_literacyProperty() {
        return l_literacy;
    }

    public void setL_literacy(String l_literacy) {
        this.l_literacy.set(l_literacy);
    }

    public String getC_education() {
        return c_education.get();
    }

    public StringProperty c_educationProperty() {
        return c_education;
    }

    public void setC_education(String c_education) {
        this.c_education.set(c_education);
    }

    public String getE_studies() {
        return e_studies.get();
    }

    public StringProperty e_studiesProperty() {
        return e_studies;
    }

    public void setE_studies(String e_studies) {
        this.e_studies.set(e_studies);
    }

    public String getMathematics_cs() {
        return mathematics_cs.get();
    }

    public StringProperty mathematics_csProperty() {
        return mathematics_cs;
    }

    public void setMathematics_cs(String mathematics_cs) {
        this.mathematics_cs.set(mathematics_cs);
    }

    public String getEnglish_cs() {
        return english_cs.get();
    }

    public StringProperty english_csProperty() {
        return english_cs;
    }

    public void setEnglish_cs(String english_cs) {
        this.english_cs.set(english_cs);
    }

    public String getScience_cs() {
        return science_cs.get();
    }

    public StringProperty science_csProperty() {
        return science_cs;
    }

    public void setScience_cs(String science_cs) {
        this.science_cs.set(science_cs);
    }

    public String getRme_cs() {
        return rme_cs.get();
    }

    public StringProperty rme_csProperty() {
        return rme_cs;
    }

    public void setRme_cs(String rme_cs) {
        this.rme_cs.set(rme_cs);
    }

    public String getFante_cs() {
        return fante_cs.get();
    }

    public StringProperty fante_csProperty() {
        return fante_cs;
    }

    public void setFante_cs(String fante_cs) {
        this.fante_cs.set(fante_cs);
    }

    public String getS_studies_cs() {
        return s_studies_cs.get();
    }

    public StringProperty s_studies_csProperty() {
        return s_studies_cs;
    }

    public void setS_studies_cs(String s_studies_cs) {
        this.s_studies_cs.set(s_studies_cs);
    }

    public String getBdt_cs() {
        return bdt_cs.get();
    }

    public StringProperty bdt_csProperty() {
        return bdt_cs;
    }

    public void setBdt_cs(String bdt_cs) {
        this.bdt_cs.set(bdt_cs);
    }

    public String getIct_cs() {
        return ict_cs.get();
    }

    public StringProperty ict_csProperty() {
        return ict_cs;
    }

    public void setIct_cs(String ict_cs) {
        this.ict_cs.set(ict_cs);
    }

    public String getNumeracy_cs() {
        return numeracy_cs.get();
    }

    public StringProperty numeracy_csProperty() {
        return numeracy_cs;
    }

    public void setNumeracy_cs(String numeracy_cs) {
        this.numeracy_cs.set(numeracy_cs);
    }

    public String getC_art_cs() {
        return c_art_cs.get();
    }

    public StringProperty c_art_csProperty() {
        return c_art_cs;
    }

    public void setC_art_cs(String c_art_cs) {
        this.c_art_cs.set(c_art_cs);
    }

    public String getL_literacy_cs() {
        return l_literacy_cs.get();
    }

    public StringProperty l_literacy_csProperty() {
        return l_literacy_cs;
    }

    public void setL_literacy_cs(String l_literacy_cs) {
        this.l_literacy_cs.set(l_literacy_cs);
    }

    public String getC_education_cs() {
        return c_education_cs.get();
    }

    public StringProperty c_education_csProperty() {
        return c_education_cs;
    }

    public void setC_education_cs(String c_education_cs) {
        this.c_education_cs.set(c_education_cs);
    }

    public String getE_studies_cs() {
        return e_studies_cs.get();
    }

    public StringProperty e_studies_csProperty() {
        return e_studies_cs;
    }

    public void setE_studies_cs(String e_studies_cs) {
        this.e_studies_cs.set(e_studies_cs);
    }
}
