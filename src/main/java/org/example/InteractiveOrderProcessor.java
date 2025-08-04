package org.example;

import java.util.*;

public class InteractiveOrderProcessor {
    public static void main(String[] args) {
        // Initialize Scanner
        Scanner scanner = new Scanner(System.in);

        // Constants
        final double GOLD_DISCOUNT = 0.15;
        final double SILVER_DISCOUNT = 0.10;
        final double QUANTITY_DISCOUNT = 0.05;
        final double SMALL_ORDER_THRESHOLD = 25.00;
        final double SMALL_ORDER_SURCHARGE = 3.00;

        // Get Order Data from User
        System.out.println("\n--- Enter Order Details ---");
        System.out.print("Enter unitPrice: ");
        double unitPrice = scanner.nextDouble();

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        System.out.print("Is customer a member? (true/false): ");
        boolean isMember = scanner.nextBoolean();
        scanner.nextLine();

        System.out.print("Enter customer tier (Regular/Silver/Gold): ");
        String customerTier = scanner.nextLine();

        System.out.print("Enter shipping zone (ZoneA/ZoneB/ZoneC/Unknown): ");
        final String shippingZone = scanner.nextLine();

        System.out.print("Enter discount code (SAVE10/FREESHIP/\"\" for none): ");
        String discountCode = scanner.nextLine();

        System.out.println("\n--- Order Details ---");
        System.out.printf("Unit Price: $%.2f%n", unitPrice);
        System.out.printf("Quantity: %d%n", quantity);
        System.out.printf("Is Member: %b%n", isMember);
        System.out.printf("Customer Tier: %s%n", customerTier);
        System.out.printf("Shipping Zone: %s%n", shippingZone);
        System.out.printf("Discount Code: %s%n", discountCode);

        System.out.println("\n--- Calculation Steps ---");

        // Subtotal Calculation
        double subTotal = unitPrice * quantity;
        System.out.printf("Initial Subtotal: $%.2f%n", subTotal);

        // Tier-Based Discount
        double total;
        if (customerTier.equalsIgnoreCase("Gold")) {
            total = subTotal * (1 - GOLD_DISCOUNT);
        } else if (customerTier.equalsIgnoreCase("Silver")) {
            total = subTotal * (1 - SILVER_DISCOUNT);
        } else {
            total = subTotal;
        }
        System.out.printf("Total after Tier Discount: $%.2f%n", total);

        // Quantity Discount
        if (quantity >= 5) {
            total *= 1 - QUANTITY_DISCOUNT;
        }
        System.out.printf("Total after Quantity Discount: $%.2f%n", total);

        // Promotional Code Application
        double shippingCost = 0;
        if (discountCode.equals("SAVE10") && total > 75) {
            total -= 10;
        } else if (discountCode.equalsIgnoreCase("FREESHIP")) {
            shippingCost = 0;
        }
        System.out.printf("Total after Promo Code: $%.2f%n", total);

        // Small Order Surcharge
        total = (total < SMALL_ORDER_THRESHOLD) ? total + SMALL_ORDER_SURCHARGE : total;
        System.out.printf("Total after Small Order Surcharge (if applicable): $%.2f%n", total);

        // Shipping Cost Calculation
        if (!discountCode.equalsIgnoreCase("FREESHIP")) {
            switch (shippingZone) {
                case "ZoneA":
                    shippingCost = 5.0;
                    break;
                case "ZoneB":
                    shippingCost = 12.5;
                    break;
                case "ZoneC":
                    shippingCost = 20.0;
                    break;
                default:
                    shippingCost = 25.0;
            }
        }
        System.out.printf("Shipping Cost: $%.2f (%s)%n", shippingCost, shippingZone);

        // Final Total
        double finalOrderTotal = total + shippingCost;
        System.out.printf("%nFinal Order Total: $%.2f%n", finalOrderTotal);

        stringEqualityDemo();

        scanner.close();
    }

    public static void stringEqualityDemo() {
        // Get Strings from User
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- String Equality Demo ---");

        // String Equality Demonstrations
        System.out.print("First string for comparison: ");
        String firstString = scanner.nextLine();

        System.out.print("Second string for comparison: ");
        String secondString = scanner.nextLine();

        System.out.printf("\nString 1: \"%s\"%n", firstString);
        System.out.printf("String 2: \"%s\"%n", secondString);

        System.out.println("String 1 == String 2: " + (firstString == secondString));
        System.out.println("String 1.equals(String 2): " + firstString.equals(secondString));
        System.out.println("String 1.equalsIgnoreCase(String 2): " + firstString.equalsIgnoreCase(secondString));

        System.out.println("\nThe '==' compares references while '.equals()' compares actual String content.");
    }
}