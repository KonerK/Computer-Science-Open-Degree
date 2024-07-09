// CONTEXT

// Percolation:
// Given a composite systems comprised of randomly distributed insulating and metallic materials:
// what fraction of the materials need to be metallic so that the composite system is an electrical conductor?
// Given a porous landscape with water on the surface (or oil below),
// under what conditions will the water be able to drain through to the bottom (or the oil to gush through to the surface)?
// Scientists have defined an abstract process known as percolation to model such situations.

// The Problem:
// In a famous scientific problem, researchers are interested in the following question:
// if sites are independently set to be open with probability p (and therefore blocked with probability 1 − p),
// what is the probability that the system percolates? When p equals 0, the system does not percolate; when p equals 1, the system percolates.
// When n is sufficiently large, there is a threshold value p* such that
// when p < p* a random n-by-n grid almost never percolates,
// and when p > p*, a random n-by-n grid almost always percolates.
// No mathematical solution for determining the percolation threshold p* has yet been derived.
// Your task is to write a computer program to estimate p*.
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF weightedQuickUnionUF;
    private boolean [][] percolationGrid;
    static final boolean closed = false;
    static final boolean open = true;
    private int numberOfOpenSites = 0;
    private final int gridLength;


    // Create an nxn grid, with all sites initially blocked.
    public Percolation(int n){
        if (n<=0){
            throw new IllegalArgumentException("n must not be smaller than one.");
        }else{
            int virtualGridBlocks = 2;
            this.weightedQuickUnionUF = new WeightedQuickUnionUF(n+virtualGridBlocks);
            this.gridLength = n;
            this.percolationGrid = new boolean[this.gridLength][this.gridLength];

            for (int i = 0; i<this.gridLength; i++){
                for (int j = 0; j<this.gridLength; j++){
                    this.percolationGrid[i][j] = closed;
                }
            }
            for (int i=0; i<this.gridLength; i++){
                this.weightedQuickUnionUF.union(0, i+1);
            }

            for (int i=(gridLength*gridLength); i >= (gridLength*gridLength+1)-4 ; i--){
                this.weightedQuickUnionUF.union((gridLength*gridLength+1), i+1);
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        if (row <= 0 || row >= this.gridLength || col<=0 || col >= this.gridLength){
            throw new IllegalArgumentException("Index out of range.");
        }else{
            if (this.percolationGrid[row][col] != open){
                this.percolationGrid[row][col] = open;
                numberOfOpenSites++;
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if (row <= 0 || row >= this.gridLength || col<=0 || col >= this.gridLength){
            throw new IllegalArgumentException("Index out of range.");
        }else{
            return this.percolationGrid[row][col];
        }
    }

    // is the site (row, col) full (closed)?
    public boolean isFull(int row, int col){
        if (row <= 0 || row >= this.gridLength || col<=0 || col >= this.gridLength){
            throw new IllegalArgumentException("Index out of range.");
        }else{
            return !(this.percolationGrid[row][col]);
        }
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return this.numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates(){
        return false;
    }

    // test client (optional)
    public static void main(String[] args){

    }

}