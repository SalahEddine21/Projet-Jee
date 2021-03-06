package com.sdz.database;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sdz.Beans.Changement;
import com.sdz.Beans.Etudiant;
import com.sdz.Beans.Etudiant_Note;
import com.sdz.Beans.Seance_Presence;

public class Operations_Etudiant {

	private static String SQL_SELECT_STUDENT = " select * from etudiants where id_etd = ? ";
	private static String SQL_SELECT_ABSENCE_PRESENCE_ETD = "select seances.date_seance as date, seances.heure_seance as heure, modules.titre_mod as module, absences.present as presence, absences.justifier as justifier from etudiants, absences, seances, modules where etudiants.id_etd = absences.id_etd and absences.id_seance = seances.id_seance and seances.id_mod = modules.id_mod and etudiants.id_etd = ? ORDER BY date ASC";
	private static String SQL_SELECT_NOTES = "select modules.titre_mod as module, etd_eval.note_etd as note from evaluations, etd_eval, modules where etd_eval.id_eval = evaluations.id_eval and evaluations.id_mod = modules.id_mod and etd_eval.id_etd = ? ";
	private static String SQL_SELECT_CHANGES = "select id_groupe_src as src, date_ch as date from changements where id_etd = ?";
	private static String SQL_SELECT_ETD = "select * from etudiants where num_etd = ?";
	private static String SQL_CHECK_CHANGEMENT = " select * from changements where id_etd = ? ";
	private static String SQL_UPDATE_GRP = " update etudiants set id_groupe = ? where id_etd = ? ";
	private static String SQL_INSERT_STUDENT = " insert into etudiants (num_etd, nom_etd, prenom_etd, email_etd, id_groupe, pass_etd) values (?,?,?,?,?,?) ";
	private static String SQL_DELETE_STDS = " delete from etudiants where id_groupe = ? ";
	
	public static Etudiant getStudent(int id_etd) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;	
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_SELECT_STUDENT,connection,false,id_etd);	
		result = preparedStatement.executeQuery();			
		Etudiant e = null;
		
		if(result.next()){
			e = new Etudiant();
			e.setId(result.getInt("id_etd"));
			e.setNum(result.getString("num_etd"));
			e.setNom(result.getString("nom_etd"));
			e.setPrenom(result.getString("prenom_etd"));
			e.setEmail(result.getString("email_etd"));
			e.setId_groupe(result.getInt("id_groupe"));
		}
		
		return e;
	}
	
	
	public static List<Seance_Presence> getPresence_Student(int id_etd) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;	
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_SELECT_ABSENCE_PRESENCE_ETD,connection,false,id_etd);	
		result = preparedStatement.executeQuery();	
		
		List<Seance_Presence> etd_presences = new ArrayList<Seance_Presence>();
		Seance_Presence sp;
		
		while(result.next()){
			sp = new Seance_Presence();
			sp.setDate(result.getDate("date"));
			sp.setHeure(result.getString("heure"));
			sp.setTitre(result.getString("module"));
			sp.setPresent(result.getInt("presence"));
			sp.setJustifier(result.getInt("justifier"));
			
			etd_presences.add(sp);
		}
		return etd_presences;
	}
	
	public static List<Etudiant_Note> getMarks_Student(int id_etd) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;	
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_SELECT_NOTES,connection,false,id_etd);	
		result = preparedStatement.executeQuery();		
		
		List<Etudiant_Note> etd_notes = new ArrayList<Etudiant_Note>();
		Etudiant_Note en;
		
		while(result.next()){
			en = new Etudiant_Note();
			en.setTitre(result.getString("module"));
			en.setNote(result.getFloat("note"));
			
			etd_notes.add(en);
		}
		return etd_notes;
	}
	
	public static Changement getChangement(int id_etd) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;	
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_SELECT_CHANGES,connection,false,id_etd);	
		result = preparedStatement.executeQuery();	
		Changement ch = null;
		
		if(result.next()){
			ch = new Changement();
			ch.setGroupe_src(result.getInt("src"));
			ch.setDate_ch(result.getDate("date"));
		}
		return ch;
	}
	
	public static Etudiant getEtdByNum(String num) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;	
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_SELECT_ETD,connection,false,num);	
		result = preparedStatement.executeQuery();	
		
		Etudiant etd = null;
		etd = new Etudiant();
		
		if(result.next()){
			etd.setId(result.getInt("id_etd"));
			etd.setNum(result.getString("num_etd"));
			etd.setNom(result.getString("nom_etd"));
			etd.setPrenom(result.getString("prenom_etd"));
			etd.setEmail(result.getString("email_etd"));
			etd.setId_groupe(result.getInt("id_groupe"));
			etd.setPasse(result.getString("pass_etd"));
		}
		
		return etd;
	}
	
	public static int checkOldCh(int id_etd) throws Exception{
		Changement ch = getChangement(id_etd);
		return ch == null ? 0:1;
	}
	
	public static void UpdateGrp(int id_etd, int id_groupe) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_UPDATE_GRP,connection,false,id_groupe, id_etd );
		int statuts = preparedStatement.executeUpdate();
		if(statuts == 0) throw new Exception("insert data error !");		
	}
	
	public static void InsertStudents(int id_groupe, String chemin) throws Exception{
		Connection con=null;
		Etudiant e;
		con = Connection_Database.getConnecion();
        PreparedStatement pstm = null;
        FileInputStream input = new FileInputStream(chemin);
        XSSFWorkbook my_xls_workbook = new XSSFWorkbook(input);    
        XSSFSheet my_worksheet = my_xls_workbook.getSheetAt(0); 
        Iterator<Row> rowIterator = my_worksheet.iterator(); 
        
        while(rowIterator.hasNext()) {
            Row row = rowIterator.next();   
            Iterator<Cell> cellIterator = row.cellIterator();
            Cell cell = cellIterator.next();
            e = new Etudiant();
            e.setNum(String.valueOf(cell.getNumericCellValue()));
            Cell cell1 = cellIterator.next();
            e.setNom(cell1.getStringCellValue());
            Cell cell2 = cellIterator.next();
            e.setPrenom(cell2.getStringCellValue());
            Cell cell3 = cellIterator.next();
            e.setEmail(cell3.getStringCellValue());
            e.setId_groupe(id_groupe);
            Cell cell4 = cellIterator.next();
            e.setPasse(cell4.getStringCellValue());
            
            insertStudent(e, con);
        }
        input.close(); 		
	}
	
	public static void insertStudent(Etudiant e, Connection c) throws Exception{
		PreparedStatement preparedStatement=null;
		preparedStatement = getPreparedStatement(SQL_INSERT_STUDENT,c,false,e.getNum(), e.getNom(), e.getPrenom(), e.getEmail(), e.getId_groupe(), e.getPasse());
		int statuts = preparedStatement.executeUpdate();
		if(statuts == 0) throw new Exception("insert data error !");
		preparedStatement.close();
	}
	
	public static void DeleteStudents(int id_groupe) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_DELETE_STDS,connection,false,id_groupe);
		int statuts = preparedStatement.executeUpdate();	
		if (statuts == 0) throw new Exception("cannot insert data");		
	}
	
	//--------------------------------------------------------------------------------------------------------------------------//
	private static PreparedStatement getPreparedStatement(String query,Connection connection,boolean ind,Object...objects) throws Exception{
		try{
			PreparedStatement prestat = connection.prepareStatement(query, ind ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
			
			for(int i = 0; i < objects.length; i++)
				prestat.setObject(i+1, objects[i]);
			return prestat;
		}catch(SQLException e){
			throw new Exception("problème lors du chargement de la requète !");
		}
	}
}
