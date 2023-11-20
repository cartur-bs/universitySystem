package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import db.DB;

public class Application {

	public static void main(String[] args) {

		Connection con = DB.getConnection();
		Statement st = null;
		ResultSet rs = null;

		Scanner sc = new Scanner(System.in);
		System.out.println("Do you want to add a new name? (y/n)");
		char response = sc.next().charAt(0);
		if (response == 'y') {
			System.out.println("Add student ID");
			int NewIdAluno = sc.nextInt();
			System.out.println("Add subject ID");
			int NewIdDisciplina = sc.nextInt();
			System.out.println("Add student grade");
			int NewNota = sc.nextInt();
			System.out.println("Add date(YYYY-MM-DD)");
			String NewData = sc.next();
			sc.close();
			try {
				PreparedStatement stt = con.prepareStatement("INSERT INTO faculdade.historico "
						+ "(idAluno, idDisciplina, nota, dataHistorico)" + "VALUES" + "(?, ?,?,?)");
				stt.setInt(1, NewIdAluno);
				stt.setInt(2, NewIdDisciplina);
				stt.setInt(3, NewNota);
				stt.setString(4, NewData);
				stt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println();
			System.out.println("New row added, check your new table: ");
			System.out.println();
			try {
				st = con.createStatement();
				rs = st.executeQuery("select * from faculdade.historico");

				while (rs.next()) {
					System.out.println(rs.getInt("idAluno") + " | " + rs.getInt("idDisciplina") + " | "
							+ rs.getInt("nota") + " | " + rs.getString("dataHistorico"));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			

		} else {
			System.out.println("No rows added!");
		}

	}
}