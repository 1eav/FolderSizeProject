package FolderSize;

import java.util.Scanner;
import java.io.File;

public class FoldersProject {
    int totalFolder = 0;
    int totalFile = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        mainLoop:
        while (true) {
            System.out.println("------------------------------------------ --");
            System.out.println("Select a category to perform the operation: ");
            System.out.println("1. Find out directory information");
            System.out.println("2. Create directory");
            System.out.println("3. Remove directory");
            System.out.println("4. Determine the size of drive C:");
            System.out.println("0. Exit the program");
            System.out.print("Select categories: ");
            int command;

            if (in.hasNextInt()) {
                command = in.nextInt();
                in.nextLine();
            } else {
                System.out.println("Invalid input. Please select a category");
                in.nextLine();
                continue;
            }

            switch (command) {
                case 1:
                    System.out.println("FOLDER PROPERTY");
                    System.out.println("Enter the path to the folder or file, for example: \nC:\\Folder");
                    System.out.print("Input: ");
                    String path = new Scanner(System.in).nextLine();
                    try {
                        FoldersProject size = new FoldersProject();
                        long fileSizeByte = size.getFolderSize(new File(path));
                        if (fileSizeByte < 1024) {
                            System.out.println(">Folder size: " + fileSizeByte + " bytes");
                        } else if (fileSizeByte > 1024 && fileSizeByte < 1048576) {
                            System.out.println(">Folder size: " + (int) fileSizeByte / 1024 + " kilobytes");
                        } else if (fileSizeByte > 1048576 && fileSizeByte < 1073741824) {
                            System.out.println(">Folder size: " + (int) fileSizeByte / (1024 * 1024) + " megabytes");
                        } else if (fileSizeByte > 1024 * 1024 * 1024) {
                            System.out.println(">Folder size: " + (int) fileSizeByte / (1024 * 1024 * 1024) + " gigabytes");
                        }
                        System.out.println(">Total number of folders: " + size.getTotalFolder());
                        System.out.println(">Total number of files: " + size.getTotalFile());
                    } catch (Exception e) {
                        System.out.println("Error. Folder is missing");
                    }
                    break;
                case 2:
                    System.out.println("CREATE FOLDER");
                    System.out.println("Enter a folder name, for example: \nC:\\Folder");
                    System.out.print("Input: ");
                    File pathNew = new File(new Scanner(System.in).nextLine());
                    try {
                        if (!pathNew.exists()) {
                            pathNew.mkdir();
                            System.out.println("Folder created");
                        } else {
                            System.out.println("The folder already exists");
                        }
                    } catch (Exception e) {
                        System.out.println("Data error");
                    }
                    break;
                case 3:
                    System.out.println("DELETE FOLDER");
                    System.out.println("Enter a folder name, for example: \nC:\\Folder");
                    System.out.print("Input: ");
                    File pathDel = new File(new Scanner(System.in).nextLine());
                    try {
                        if (pathDel.exists()) {
                            pathDel.delete();
                            System.out.println("Folder deleted.");
                        } else {
                            System.out.println("This folder does not exist");
                        }
                    } catch (Exception e) {
                        System.out.println("Data error");
                    }
                    break;
                case 4:
                    System.out.println("INFORMATION ABOUT DISK C:");
                    File c = new File("C:\\");
                    long totalSpace = c.getTotalSpace();
                    System.out.println("Disk C: - Total space = " + totalSpace + " bytes");
                    break;
                case 0:
                    System.out.println("\nExit the program");
                    break mainLoop;
                default:
                    System.out.println("Invalid input. Please select a category");
            }
        }
    }

    public long getFolderSize(File path) {
        totalFolder++;
        System.out.println("FOLDER INFO: " + "<" + path.getName() + ">");
        long foldersize = 0;

        File[] fileList = path.listFiles();
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isDirectory()) {
                foldersize += getFolderSize(fileList[i]);
            } else {
                totalFile++;
                foldersize += fileList[i].length();
            }
        }
        return foldersize;
    }

    public int getTotalFolder() {
        return totalFolder;
    }

    public int getTotalFile() {
        return totalFile;
    }
}