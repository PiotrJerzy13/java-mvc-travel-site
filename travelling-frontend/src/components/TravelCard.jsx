import { useState } from "react";
import { travellingApi } from "../services/travellingApi";
import EditReviewInline from "./EditReviewInline";

export default function TravelCard({ item, onUpdate }) {
    const formatDate = (dateStr) => {
        const date = new Date(dateStr);
        return date.toLocaleDateString('en-US', { month: 'short', day: 'numeric', year: 'numeric' });
    };

    const renderStars = (rating) => {
        if (!rating) return null;
        const fullStars = Math.floor(rating);
        const hasHalf = rating % 1 >= 0.5;

        return (
            <div style={styles.starsContainer}>
                {[...Array(5)].map((_, i) => (
                    <span
                        key={i}
                        style={{
                            color: i < fullStars ? '#fbbf24' : (i === fullStars && hasHalf ? '#fbbf24' : '#e5e7eb'),
                            fontSize: '16px'
                        }}
                    >
                        {i < fullStars ? '★' : (i === fullStars && hasHalf ? '★' : '☆')}
                    </span>
                ))}
                <span style={styles.ratingText}>{rating.toFixed(1)}</span>
            </div>
        );
    };

    return (
        <div style={styles.card}>
            <div style={styles.cardHeader}>
                <div>
                    <h3 style={styles.destination}>📍 {item.destination}</h3>
                    <div style={styles.date}>🗓️ {formatDate(item.date)}</div>
                </div>
                {renderStars(item.ratingSummary)}
            </div>

            {item.description && (
                <p style={styles.description}>{item.description}</p>
            )}

            <EditReviewInline item={item} onSaved={onUpdate} />
        </div>
    );
}

const styles = {
    card: {
        background: 'white',
        padding: '20px',
        borderRadius: '12px',
        boxShadow: '0 2px 8px rgba(0,0,0,0.08)',
        transition: 'transform 0.2s, box-shadow 0.2s'
    },
    cardHeader: {
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'start',
        marginBottom: '12px'
    },
    destination: {
        fontSize: '22px',
        fontWeight: '600',
        margin: '0 0 6px 0',
        color: '#1a1a1a'
    },
    date: {
        fontSize: '14px',
        color: '#6b7280'
    },
    starsContainer: {
        display: 'flex',
        alignItems: 'center',
        gap: '4px'
    },
    ratingText: {
        fontSize: '14px',
        color: '#6b7280',
        marginLeft: '4px'
    },
    description: {
        color: '#374151',
        fontSize: '15px',
        lineHeight: '1.6',
        margin: '0 0 12px 0',
        padding: '12px',
        background: '#f9fafb',
        borderRadius: '8px',
        borderLeft: '3px solid #4f46e5'
    }
};